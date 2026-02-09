# SecuBank

SecuBank is a secure banking application designed to help users manage accounts, transactions, and financial activity with an emphasis on security, clarity, and reliability.

The application was built to explore secure application design using Spring Boot and Spring Security, while modeling real-world banking concepts such as account ownership, transaction records, and access control.

---

## Key Features

### Account Management
- Create and manage user accounts
- View balances and detailed transaction history
- Support for multiple account types (checking, savings, credit)

### Transactions
- Transfer funds between accounts
- View transaction details and receipts
- Persistent transaction history for auditing and review

### Security
- Secure authentication using Spring Security
- Role-based access control
- Real-time transaction alerts
- Ability to lock and unlock cards

### User Experience
- Intuitive, structured interface
- Dashboard and notifications

---

## Tech Stack
- Java
- Spring Boot
- Spring Security
- MySQL
- Thymeleaf
- HTML
- CSS
- Figma
- Jira

---

## Architecture & Implementation Notes

- Spring Security is used to manage authentication and authorization, ensuring protected access to sensitive routes and data.
- Business logic is separated into service layers to keep controllers thin and maintainable.
- Transaction data is persisted with clear relational modeling to support traceability and historical review.
- Thymeleaf is used for server-side rendering to maintain predictable state and reduce frontend complexity.

---

## What I Learned

- Designing secure authentication and authorization flows using Spring Security
- Modeling financial data with relational integrity in mind
- Structuring backend applications using layered architecture
- Balancing user experience with security constraints

---

## Demo & Assets
- Screenshots of key flows
![Screenshot 2024-07-24 102403](https://github.com/user-attachments/assets/5a7167cf-169a-4948-870b-213e6b846eff)

- Entity-Relationship (EER) diagram illustrating the data model
![SecuBank EER_](https://github.com/user-attachments/assets/21192f63-49e9-4039-98c2-5b5e75c23867)
