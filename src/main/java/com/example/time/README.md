# British Spoken Time

A simple Java application that converts a given time into its British spoken form.

For example:

- `01:00` → `one o'clock`
- `02:05` → `five past two`
- `04:15` → `quarter past four`
- `07:30` → `half past seven`
- `07:35` → `twenty-five to eight`
- `09:45` → `quarter to ten`
- `00:00` → `midnight`
- `12:00` → `noon`
- `06:32` → `six thirty-two`

## Requirements

- Java 21 or later
- Maven 3.9 or later
- Git

Check the installed versions:

```bash
java -version
mvn -version
git --version
```

## Getting Started

Clone the repository:

```bash
git clone https://github.com/rupinder-tech/british-spoken-time.git
cd british-spoken-time
```

## Project Structure

```text
british-spoken-time/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/java/com/example/time/
    │   ├── Main.java
    │   ├── TimeParser.java
    │   └── BritishSpokenTime.java
    └── test/java/com/example/time/
        ├── TimeParserTest.java
        └── BritishSpokenTimeTest.java
```

## Application Design

The application is divided into separate responsibilities.

### Main

`Main` is the command-line entry point. It reads the user's input, passes it to `TimeParser`, passes the parsed `LocalTime` to `BritishSpokenTime`, and prints the result.

### TimeParser

`TimeParser` validates and parses the user's input into a Java `LocalTime` object.

For example:

```text
07:35
```

Invalid input such as `25:00` or `not-a-time` is rejected.

### BritishSpokenTime

`BritishSpokenTime` contains the conversion logic for British spoken time, including:

- `o'clock`
- `past`
- `half past`
- `quarter past`
- `to`
- `quarter to`
- `midnight`
- `noon`

The conversion logic is kept separate from input parsing and command-line handling.

## Conversion Rules

### Midnight

```text
00:00 → midnight
```

### Noon

```text
12:00 → noon
```

### Exact hour

```text
01:00 → one o'clock
11:00 → eleven o'clock
```

### Minutes past the hour

```text
02:05 → five past two
03:10 → ten past three
04:15 → quarter past four
05:20 → twenty past five
06:25 → twenty-five past six
```

### Half past

```text
07:30 → half past seven
```

### Minutes to the next hour

```text
07:35 → twenty-five to eight
08:40 → twenty to nine
09:45 → quarter to ten
10:50 → ten to eleven
11:55 → five to twelve
```
### Non-five-minute times

Times that are not multiple of five are spoken directly using the hour and minute.

```text
06:12 → six twelve
06:32 → six thirty-two
```

For `to` expressions, the minutes remaining until the next hour are calculated as:

```text
60 - minutes
```

For example:

```text
07:35

60 - 35 = 25

twenty-five to eight
```

The next hour is calculated using:

```java
(hour + 1) % 24
```

This handles the transition from 23:00 to 00:00.

For example:

```text
23:55 → five to twelve
```

## Example Inputs and Outputs

| Input | Output |
|---|---|
| `01:00` | `one o'clock` |
| `02:05` | `five past two` |
| `03:10` | `ten past three` |
| `04:15` | `quarter past four` |
| `05:20` | `twenty past five` |
| `06:25` | `twenty-five past six` |
| `06:32` | `six thirty-two` |
| `07:30` | `half past seven` |
| `07:35` | `twenty-five to eight` |
| `08:40` | `twenty to nine` |
| `09:45` | `quarter to ten` |
| `10:50` | `ten to eleven` |
| `11:55` | `five to twelve` |
| `00:00` | `midnight` |
| `12:00` | `noon` |

## Running the Tests

The project uses JUnit 5 for unit testing.

Run all tests:

```bash
mvn test
```

A successful build should finish with:

```text
BUILD SUCCESS
```

`TimeParserTest` verifies valid parsing, whitespace handling, invalid values, invalid text, and blank input.

`BritishSpokenTimeTest` verifies midnight, noon, exact hours, past times, half past, `to` times, quarter expressions, hour rollover, and non-five-minute times such as `06:32`.

## Compiling the Application

```bash
mvn compile
```

The compiled classes are generated under:

```text
target/classes/
```

## Running the Application

After compiling:

```bash
java -cp target/classes com.example.time.Main
```

The application prompts for a time:

```text
Enter time (HH:mm):
```

Example:

```text
Enter time (HH:mm): 07:35
twenty-five to eight
```

Another example:

```text
Enter time (HH:mm): 09:45
quarter to ten
```

And:

```text
Enter time (HH:mm): 00:00
midnight
```

## Input Format

The application expects a time in 24-hour format:

```text
HH:mm
```

Examples:

```text
07:30
12:05
18:45
23:55
```

A single-digit hour is also accepted:

```text
7:30
```

Invalid input results in an error message.

## Maven Commands

Run tests:

```bash
mvn test
```

Compile:

```bash
mvn compile
```

Clean and run tests:

```bash
mvn clean test
```

Clean and compile:

```bash
mvn clean compile
```

## Technologies Used

- Java 21
- Maven
- JUnit 5
- Java Time API (`java.time.LocalTime`)

No external framework is required to run the application.

## Testing Approach

The project uses unit tests to verify individual components.

The tests cover valid and invalid inputs and important boundary cases such as:

- Midnight
- Noon
- Exact hours
- Half past
- Quarter past
- Quarter to
- Transition from 23:00 to 00:00
- Non-five-minute times

This allows the parsing and conversion logic to be tested independently from the command-line interface.

## Design

The application follows a simple separation of responsibilities:

```text
User Input
    |
    v
  Main
    |
    v
TimeParser
    |
    v
 LocalTime
    |
    v
BritishSpokenTime
    |
    v
Spoken Output
```

`Main` handles user interaction.

`TimeParser` handles input validation and parsing.

`BritishSpokenTime` handles the conversion from `LocalTime` to the required spoken representation.

The tests verify the parser and conversion logic independently.

## License

This project was created as a Java coding assignment.
