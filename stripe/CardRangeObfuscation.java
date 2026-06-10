package stripe;

/*
Problem 2: Card Range Obfuscation
Problem Description

Payment card numbers consist of 8-19 digits, with the first 6 digits referred to as the Bank Identification Number (BIN).

For a given BIN, all 16-digit card numbers starting with that BIN are considered to be in the BIN range. For example, the BIN 424242 corresponds to card numbers from 4242420000000000 (inclusive) through 4242429999999999 (inclusive).

Stripe’s card metadata API may return partial coverage of this BIN range, by providing a list of intervals mapping to card brands (e.g., VISA, MASTERCARD). However, these intervals may have gaps (at the beginning, middle, or end of the BIN range), which can be exploited by fraudsters to probe for valid cards.

My task was to fill in missing intervals so that the returned intervals fully cover the entire BIN range, with no gaps, and return them in sorted order.

Input Format

1. Line 1: A 6-digit BIN.

2. Line 2: A positive integer n, the number of intervals.

3. Next n lines: Each line represents one interval in the format:

start,end,brand

where:

- start and end are 10-digit numbers representing the offset within the BIN range (inclusive).

- brand is an alphanumeric string representing the card brand.

Output Format

Return a list of sorted, gap-free intervals, each covering a contiguous portion of the BIN range, formatted as:

start,end,brand

Where start and end are now full 16-digit card numbers (BIN + 10-digit offset). The output intervals must be sorted by start.

Example

Input

777777
2
1000000000,3999999999,VISA
4000000000,5999999999,MASTERCARD
Output

7777770000000000,7777773999999999,VISA
7777774000000000,7777775999999999,MASTERCARD
Notes

If the input intervals already cover the full BIN range, just return them sorted.

If there are gaps, fill them with intervals extending from previous/next coverage so that no range is uncovered.

Be careful with inclusive endpoints: ensure full coverage from BIN0000000000 to BIN9999999999.


*/

import java.util.*;

public class CardRangeObfuscation {
    
    record Interval(long start, long end, String brand){};

    private List<Interval> getIntervals(long bin, List<Interval> intervals){
        long binStart = bin * 10_000_000_000L;
        long binEnd = binStart + 9_999_999_999L;
        intervals.sort((a,b) -> Long.compare(a.start(),b.start()));

        long current = binStart;
        List<Interval> output = new ArrayList<>();

        for(Interval interval: intervals){
            if(current<interval.start()){
                output.add(new Interval(current, interval.start()-1, "Unknown"));
            }
            output.add(interval);
            current = interval.end()+1;
        }

        if(current<=binEnd){
            output.add(new Interval(current, binEnd, "Unknown"));
        }

        return output;
        
    }

}
