# Contributor's Guide for kosta-interpreter

Thank you for your interest in contributing to kosta-interpreter! This guide will help you set up your development environment and understand our contribution process.

## Table of Contents

- [Getting Started](#getting-started)
    - [Forking the Repository](#forking-the-repository)
    - [Cloning the Forked Repository](#cloning-the-forked-repository)
    - [Setting Up the Development Environment](#setting-up-the-development-environment)
- [Development Workflow](#development-workflow)
    - [Branch Naming Convention](#branch-naming-convention)
    - [Creating a Branch](#creating-a-branch)
    - [Committing Changes](#committing-changes)
    - [Keeping Your Fork Updated](#keeping-your-fork-updated)
    - [Creating a Pull Request](#creating-a-pull-request)
- [Code Formatting and Style Guidelines](#code-formatting-and-style-guidelines)
    - [Java Code Style](#java-code-style)
    - [Class Structure](#class-structure)
    - [Documentation Guidelines](#documentation-guidelines)
- [Testing](#testing)
    - [Running Tests](#running-tests)
    - [Writing Tests](#writing-tests)
- [Code Review Process](#code-review-process)
    - [Review Lifecycle](#review-lifecycle)
    - [Addressing Feedback](#addressing-feedback)

---

## Getting Started

### Forking the Repository

1. Go to the [kosta-interpreter GitHub repository](https://github.com/KonstantineVashalomidze/kosta-interpreter)
2. Click the "Fork" button in the top-right corner to create your own copy

### Cloning the Forked Repository

```bash
git clone https://github.com/KonstantineVashalomidze/kosta-interpreter.git
cd kosta-interpreter
```

### Setting Up the Development Environment

1. Ensure [Java 21+](https://www.oracle.com/java/technologies/downloads/) is installed:
   ```bash
   java -version
   ```

2. Install [Maven](https://maven.apache.org/download.cgi) for build management:
   ```bash
   # Verify Maven installation
   mvn -version
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Set up your IDE:
    - For IntelliJ IDEA:
        - Open the project via File > Open
        - Allow Maven to import the project dependencies
        - Set the Project SDK to Java 21
    - For Eclipse:
        - Import the project as a Maven project
        - Ensure JDK 21 compliance is configured

5. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.github.konstantinevashalomidze.interpreter.Main"
   ```

---

## Development Workflow

### Branch Naming Convention

Use lowercase with the following prefixes:

- `feat/` - for new features
- `fix/` - for bug fixes
- `refactor/` - for code refactoring
- `docs/` - for documentation updates
- `test/` - for adding or updating tests
- `perf/` - for performance improvements

Examples:
```
feat/array-support
fix/parser-edge-case
docs/readme-update
refactor/lexer-optimization
```

### Creating a Branch

```bash
# Ensure you're on the main branch
git checkout main

# Pull the latest changes
git pull origin main

# Create a new branch
git checkout -b feat/your-feature-name
```

### Committing Changes

Follow the [Conventional Commits](https://www.conventionalcommits.org/) style:

```bash
git commit -m "feat: add array literal support"
git commit -m "fix(parser): handle missing semicolons"
git commit -m "docs: update installation instructions"
```

Each commit message should:
- Be concise and descriptive
- Use present tense ("add feature" not "added feature")
- Reference issue numbers when applicable (`"fix: handle null pointer exception closes #123"`)

### Keeping Your Fork Updated

To keep your fork in sync with the main repository:

```bash
# Add the original repo as an upstream remote
git remote add upstream https://github.com/KonstantineVashalomidze/kosta-interpreter.git

# Fetch changes from upstream
git fetch upstream

# Merge changes into your local main branch
git checkout main
git merge upstream/main

# Push the updated main to your fork
git push origin main
```

### Creating a Pull Request

1. Push your branch to your fork:
   ```bash
   git push origin feat/your-feature-name
   ```

2. Navigate to your fork on GitHub
3. Click "Compare & pull request"
4. Ensure your PR description includes:
    - Purpose of the changes
    - Summary of implementation details
    - Testing performed
    - Screenshots/examples (if applicable)
    - Related issues (using GitHub keywords like "Fixes #123")
5. Add a note confirming merge/rebase completion:
   ```
   This PR has been rebased on latest main (SHA: abc123)
   ```

---

## Code Formatting and Style Guidelines

### Java Code Style

- **Naming Conventions**:
    - Class names: `PascalCase` (e.g., `TokenParser`)
    - Methods/variables: `camelCase` (e.g., `parseExpression`)
    - Constants: `UPPER_SNAKE_CASE` (e.g., `MAX_TOKEN_LENGTH`)
    - Package names: all lowercase (e.g., `com.github.konstantinevashalomidze.interpreter`)

- **Code Organization**:
    - One class per file
    - Related classes in the same package
    - Annotate overrides with `@Override`
    - Use `final` for immutable variables and parameters
    - Prefer composition over inheritance

### Class Structure

Classes should be organized in the following order:

```java
public class Example {
    // Constants
    public static final int MAX_SIZE = 100;
    
    // Static fields
    private static Logger logger = LoggerFactory.getLogger(Example.class);
    
    // Instance fields
    private int counter = 0;
    private String name;
    
    // Constructors (ascending order by parameter count)
    public Example() {
        this("default");
    }
    
    public Example(String name) {
        this.name = name;
    }
    
    // Public methods
    public void process() {
        // Implementation
    }
    
    // Private methods
    private void helperMethod() {
        // Implementation
    }
    
    // Getters and setters (at the bottom)
    public int getCounter() {
        return counter;
    }
    
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
```

### Documentation Guidelines

- All public classes and methods should have JavaDoc comments
- Document non-obvious implementation decisions with inline comments
- Use `//` for single-line comments
- Format multi-line comments with appropriate indentation:
  ```java
  /**
   * This is a multi-line comment.
   * It describes complex logic.
   */
  ```

---

## Testing

### Running Tests

Run the full test suite:
```bash
mvn test
```

Run specific tests:
```bash
mvn test -Dtest=LexerTest
```

### Writing Tests

- Test files should match source file structure and naming
- Use JUnit Jupiter 5.9.2 for testing
- Mockito 5.3.1 is available for mocking
- Test cases should be independent and repeatable
- Name test methods descriptively: `shouldReturnNullWhenInputIsEmpty()`
- Include tests for edge cases and error conditions

---

## Code Review Process

### Review Lifecycle

1. Submit PR → Automated tests run → Maintainer review → Address feedback → Approval → Merge

2. PRs must meet these criteria before review:
    - Pass all automated tests
    - Follow code style guidelines
    - Include appropriate tests
    - Have a clear PR description

### Addressing Feedback

- Address all review comments before requesting re-review
- Use fixup commits during the review process

---

