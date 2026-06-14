package rippling;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CreditCardExpenseReview {
    
    public List<Violation> evaluateRules(List<Rule> rules, List<Expense> expenses){
        EvaluationContext evaluationContext = new EvaluationContext(expenses);

        List<Violation> violations = new ArrayList<>();
        for(Rule rule: rules){
            violations.addAll(rule.evaluate(expenses, evaluationContext));
        }
        return violations;
    }

    public List<Violation> getViolations(List<Expense> expenses) {
        List<Rule> rules = List.of(
            new RestaurentLimitExpense(),
            new NoAirFairRule(),
            new NoEntertainmentRule(),
            new ExpenseLimitRule(),
            new TripTotalLimitRule(),
            new TripMealLimitRule()
        );
        List<Violation> violations = evaluateRules(rules, expenses);
        return violations;
    }
}

record Expense(String expenseId,
                    BigDecimal amount, 
                    String currency, 
                    String expenseType, 
                    String vendorType, 
                    String merchant, 
                    LocalDate transactionDate, 
                    String tripId){}

record Violation (
    String entityType, // EXPENSE/Trip
    String entityId,
    String ruleCode,
    String severity,
    String message,
    BigDecimal observedValue,
    BigDecimal threshold
){}

interface Rule {
    String code();

    List<Violation> evaluate(List<Expense> expenses, EvaluationContext contex);
}

abstract class ExpenseRule implements Rule {

    @Override
    public List<Violation> evaluate(List<Expense> expenses, EvaluationContext contex) {
        List<Violation> violations = new ArrayList<>();

        for(Expense expense: expenses){
            Violation violation = evaluateExpense(expense);

            if(violation!=null){
                violations.add(violation);
            }
        }
        return violations;
    }

    protected abstract Violation evaluateExpense(Expense expense);
}

// Rule 1: No Restaurent Expense > $75

class RestaurentLimitExpense extends ExpenseRule {
    private static final BigDecimal LIMIT = BigDecimal.valueOf(75);

    @Override
    public String code() {
        return "RESTAURENT_LIMIT";
    }

    @Override
    protected Violation evaluateExpense(Expense expense) {
        if(!"RESTAURENT".equalsIgnoreCase(expense.expenseType())){
            return null;
        }
        if(expense.amount().compareTo(LIMIT)>0){
            return new Violation("EXPENSE", expense.expenseId(), code(), "HIGH", "Restaurent expense exceeds $75", expense.amount(), LIMIT);
        }
        return null;
    }
}


// Rule 2: No Airfare

class NoAirFairRule extends ExpenseRule {

    @Override
    public String code() {
        return "NO_AIRFARE";
    }

    @Override
    protected Violation evaluateExpense(Expense expense){
        if(!"AIRFARE".equalsIgnoreCase(expense.expenseType())){
            return null;
        }
        return new Violation("EXPENSE", expense.expenseId(), code(), "HIGH", "Airfare not allowed", expense.amount(), BigDecimal.ZERO);
    }
}

// Rule 3: No Entertainment
class NoEntertainmentRule extends ExpenseRule {

    @Override
    public String code() {
        return "NO_ENTERTAINMENT";
    }

    @Override
    protected Violation evaluateExpense(Expense expense){
        if(!"ENTERTAINMENT".equalsIgnoreCase(expense.expenseType())){
            return null;
        }
        return new Violation("EXPENSE", expense.expenseId(), code(), "HIGH", "Entertainment not allowed", expense.amount(), BigDecimal.ZERO);
    }
}

// Rule 4: Expense limit
class ExpenseLimitRule extends ExpenseRule {

    private static final BigDecimal LIMIT = BigDecimal.valueOf(250); 
    @Override
    public String code(){
        return "EXPENSE_LIMIT";
    }

    @Override
    public Violation evaluateExpense(Expense expense){
        if(expense.amount().compareTo(LIMIT)>0){
            return new Violation("EXPENSE", expense.expenseId(), code(), "HIGH", "Expense exceeds $250", expense.amount(), LIMIT);
        }
        return null;
    }
}

class TripAggregate {
    String tripId;

    BigDecimal totalExpense = BigDecimal.ZERO;
    BigDecimal totalMeals = BigDecimal.ZERO;

    public TripAggregate(String tripId){
        this.tripId = tripId;
    }
}

class EvaluationContext {
    private final Map<String, TripAggregate> trips = new HashMap<>();

    public EvaluationContext(List<Expense> expenses){
        for(Expense expense: expenses){
            if(expense.tripId()==null){
                continue;
            }

            TripAggregate trip = trips.computeIfAbsent(expense.tripId(), TripAggregate::new);

            trip.totalExpense = trip.totalExpense.add(expense.amount());
            if("RESTAURENT".equalsIgnoreCase(expense.expenseType())){
                trip.totalMeals = trip.totalMeals.add(expense.amount());
            }
        }
    }

    public Map<String, TripAggregate> trips(){
        return trips;
    }
}

// Trip rules

abstract class TripRule implements Rule {

    @Override
    public List<Violation> evaluate(List<Expense> expenses, EvaluationContext evaluationContext){
        List<Violation> violations = new ArrayList<>();
        for(TripAggregate trip: evaluationContext.trips().values()){
            Violation violation = evaluateTrip(trip);
            if(violation!=null){
                violations.add(violation);
            }
        }
        return violations;
    }

    protected abstract Violation evaluateTrip(TripAggregate trip);
}

// Trip total cost rule

class TripTotalLimitRule extends TripRule {

    private static final BigDecimal LIMIT = BigDecimal.valueOf(2000);

    @Override
    public String code(){
        return "TRIP_TOTAL_LIMIT";
    }

    @Override
    protected Violation evaluateTrip(TripAggregate trip){
        if(trip.totalExpense.compareTo(LIMIT)>0){
            return new Violation("TRIP", trip.tripId, code(), "HIGH", "Trip exceeds $2000", trip.totalExpense, LIMIT);
        }
        return null;
    }
}

// Total Meal Cost

class TripMealLimitRule extends TripRule {
    private final static BigDecimal LIMIT = BigDecimal.valueOf(200);

    @Override
    public String code(){
        return "TRIP_MEAL_LIMIT";
    }

    @Override
    protected Violation evaluateTrip(TripAggregate trip){
        if(trip.totalMeals.compareTo(LIMIT)>0){
            return new Violation("TRIP", trip.tripId, code(), "MEDIUM", "Meal Cost exceeds $200", trip.totalMeals, LIMIT);
        }
        return null;
    }
}

/*
Current complexity:

Expense Rules:
O(R * N)

Trip Rules:
O(T * G)

For:

100 rules
1M expenses

This becomes expensive.

A follow-up answer:

"For large rule counts I'd move toward a Specification Pattern or 
compile rules into predicates so we don't repeatedly scan all expenses for every rule."
*/