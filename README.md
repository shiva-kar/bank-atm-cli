# Bank ATM CLI

A small console-based Java ATM simulator for practising:

- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Exception handling
- Basic transaction history

## Clone the repository

```bash
git clone https://github.com/shiva-kar/bank-atm-cli.git
cd bank-atm-cli
```

## Requirements

- Java 17 or newer (Because of Record class)
- IntelliJ IDEA or a Java-compatible terminal
This project currently uses Java records, so Java 16 or newer is required.

## Run the application

### IntelliJ IDEA
1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure the project SDK is set to Java 17 or newer.
4. Open:

   ```text
   src/me/shiva/app/Main.java
   ```

5. Run the `Main` class.
### Windows PowerShell


Run the application:
```powershell
java -cp out\classes me.shiva.app.Main
```

## Sample login
The sample account is:
```text
Account number: 1001
PIN: 1234
```

## Available ATM operations

After logging in, the ATM provides:
```text
1. Deposit
2. Withdraw
3. Check balance
4. Transaction history
5. Logout
```

The account allows three incorrect PIN attempts before the account is locked.

## Project structure

```text
src/
└── me/shiva/
    ├── app/
    │   └── Main.java
    └── bank/
        ├── ATM.java
        ├── AccountHolder.java
        ├── Bank.java
        ├── InsufficientFundsException.java
        ├── InvalidPinException.java
        ├── SavingsAccount.java
        └── systems/
            ├── Account.java
            └── Transaction.java
```

## Design overview

- `Account` stores common account state and behavior.
- `SavingsAccount` extends `Account` and enforces a minimum balance.
- `Bank` stores and retrieves accounts.
- `ATM` handles console input, authentication, menus, and user messages.
- `Transaction` stores transaction history entries.
- `Main` creates the objects and starts the application.
