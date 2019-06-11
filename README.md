# Notes
## Time Complexity Analysis
Big O 
A -> f(n)
if time taken by A for input n <= k*f(n)
### Insertion Sort
>* Worst Case Complexity: O(n^2)
>* Best Case Complexity: O(n)

### Selection Sort
>* Worst Case Complexity: O(n^2)
>* Best Case Complexity: O(n^2)

### Merge Sort
>* O(nlogn)  --> base is 2
>* Space Complexity O(n)

### Binary Search
>* Time Complexity is O(logn)  --> base is 2
>* Space Complexity in recurive algo is O(logn)

## Bubble Sort
>* Space Complexity is O(1)

## Space Complexity Analysis
>* Calculate auxilliary space required.
>* Max space requirement at any point of time.
>* Space complexity for finding <strong>nth fibonacci number</strong> using recurion is O(n)

## Arrays
>* Whenever an integer array is created, all elements are initialised to 0.
>* Whenever an boolean array is created, all elements are initialised to false.
>* Whenever a double array is created, all elements are initialised to 0.0 .
> Whenever we just print name of array, eg. sopln(a) -> [I@42a5773]. 
    >>* [ =  We are talking about arrays
    >>* I = integer array
    >>* after @ = hexadecimal memory address reference of array
>* Java has a garbage collector collector which runs periodically and destroys assigned memory locations which are not being used.
>* Passsing primitive and non-primitive data in functions varies output. In primitive the changes in value caused in function does not change the main value. But in non-primitive, like arrays, the reference of array is passed, so any changes made in values of array in function reflects in main array too.