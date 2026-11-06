# Visitor Pattern: Trip to Japan

Assignment 6 for the course **Software Design Patterns**.
A Java implementation of the **Visitor** behavioral design pattern.

## Topic

A trip to Japan is made of several kinds of parts: hotels, temples, restaurants and train tickets. The set of part types is stable, but we want to run many unrelated operations on the whole trip: calculate the budget, plan the time, and prepare a packing list.

Visitor lets us add each new operation as a separate class, without modifying any of the element classes.

## Project Structure

```
JapanTripVisitor/
├── README.md
└── src/
    └── japantrip/
        ├── Main.java
        ├── element/
        │   ├── TripElement.java
        │   ├── Hotel.java
        │   ├── Temple.java
        │   ├── Restaurant.java
        │   └── TrainTicket.java
        ├── visitor/
        │   ├── TripVisitor.java
        │   ├── BudgetVisitor.java
        │   ├── TimePlannerVisitor.java
        │   └── PackingTipsVisitor.java
        └── structure/
            └── Trip.java
```

## Pattern Components

| Component | Class | Responsibility |
|---|---|---|
| Element Interface | `TripElement` | Declares `accept(TripVisitor visitor)` |
| Concrete Element | `Hotel` | Stores hotel data, calls `visitor.visitHotel(this)` |
| Concrete Element | `Temple` | Stores temple data, calls `visitor.visitTemple(this)` |
| Concrete Element | `Restaurant` | Stores restaurant data, calls `visitor.visitRestaurant(this)` |
| Concrete Element | `TrainTicket` | Stores ticket data, calls `visitor.visitTrainTicket(this)` |
| Visitor Interface | `TripVisitor` | Declares one `visitX()` method per concrete element |
| Concrete Visitor | `BudgetVisitor` | Calculates the total cost of the trip in USD |
| Concrete Visitor | `TimePlannerVisitor` | Calculates the total time of all activities |
| Concrete Visitor | `PackingTipsVisitor` | Builds a packing and travel tips list |
| Object Structure | `Trip` | Holds the elements and lets a visitor traverse them |
| Client | `Main` | Builds a trip and applies the visitors |

## How Double Dispatch Works

1. The client calls `trip.accept(visitor)`. The `Trip` loops over its elements and calls `element.accept(visitor)`.
   This is the **first dispatch**: Java picks the `accept()` implementation by the runtime type of the element (`Hotel`, `Temple`, ...).
2. Inside `accept()`, the element calls `visitor.visitX(this)`, for example `Temple.accept()` calls `visitor.visitTemple(this)`.
   This is the **second dispatch**: Java picks the implementation of `visitTemple()` by the runtime type of the visitor (`BudgetVisitor`, `TimePlannerVisitor`, ...).

Because `this` inside `Temple` has the exact type `Temple`, the correct `visitX()` method is chosen without any `instanceof` checks. The code that runs depends on both the element type and the visitor type.

## How to Run

Requirements: JDK 17 or newer.

```
javac -d out $(find src -name "*.java")
java -cp out japantrip.Main
```

On Windows (PowerShell), compile with:

```
javac -d out src/japantrip/*.java src/japantrip/element/*.java src/japantrip/visitor/*.java src/japantrip/structure/*.java
java -cp out japantrip.Main
```

Or simply open the project in IntelliJ IDEA and run `Main`.

## Expected Output

```
=== Trip to Japan: Tokyo - Kyoto ===

--- Budget ---
  [Budget] Hotel Shinjuku Granbell              270 USD
  ...
TOTAL: 629 USD

--- Time planner ---
  [Time]   Check-in/out Shinjuku Granbell       60 min
  ...
TOTAL: 12.3 hours

--- Packing tips (3rd visitor, no element changed) ---
  * Power adapter type A/B and slippers (Tokyo)
  ...
```

## Clean Code Principles

1. **Meaningful names.** Elements are nouns (`Hotel`, `Temple`), visitors are named after the operation they perform (`BudgetVisitor`). The method names `accept` and `visitX` clearly separate the Element and Visitor responsibilities.
2. **No `instanceof` or type-checking chains.** Double dispatch fully replaces them. There is no `instanceof` anywhere in the project.
3. **Single Responsibility Principle.** Each concrete visitor implements exactly one operation across all element types (money, time, packing). Each element only stores its own data and knows how to accept a visitor.
4. **Open/Closed Principle and extensibility.** `PackingTipsVisitor` was added as the third visitor without changing `Hotel`, `Temple`, `Restaurant`, `TrainTicket` or `Trip`. A new operation means one new class.
5. **Minimal, justified encapsulation trade-off.** Visitors need data from elements, so elements expose it through public getters. All fields are `private final`, and there are no setters, so a visitor can read the state of an element but never break it.
6. **No code duplication.** Each visitor uses a small private helper method (`add`) for accumulating and printing results.
7. **No magic values and immutability.** Constants such as `HOURS_PER_MEAL` are named, and elements are immutable, so the same trip can be safely traversed by many visitors.
8. **Cohesive packages.** The code is split into `element`, `visitor` and `structure`, so each role of the pattern has its own place.

## Trade-offs of the Visitor Pattern

- Adding a new **visitor** is easy: create one class that implements `TripVisitor`.
- Adding a new **element** (for example `Museum`) is harder: a new `visitMuseum()` method must be added to `TripVisitor` and implemented in every concrete visitor.
- Elements must expose some of their data through getters so that visitors can work with them.

This is why Visitor fits best when the set of element classes is stable and the set of operations changes often.

