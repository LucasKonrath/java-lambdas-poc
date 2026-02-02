java-lambdas-poc

Requirements
- Java 17+ (Java 11+ should work as well)
- Maven 3.8+

Run
1) mvn test
2) mvn -q exec:java

What it demonstrates
- Lambdas as Runnable/Callable
- Functional interfaces (Predicate, Function, Consumer, Supplier)
- Method references
- Stream operations (map/filter/reduce/collect)
- Custom functional interface and composition
- Capturing effectively-final variables

Project layout
- src/main/java/... contains runnable demo
- src/test/java/... contains JUnit tests validating behavior
