
# Currency Service Application - Full-Stack

This project is a full-stack web application that allows users to retrieve the current exchange rate of a given currency from the National Bank of Poland (NBP) and view the history of previous currency requests.


## Table of Contents

1. [Technologies](#technologies)
2. [Installation](#installation)
3. [Backend Setup](#backend-setup)
4. [Frontend Setup](#frontend-setup)
5. [Usage](#usage)
6. [API Endpoints](#api-endpoints)
7. [License](#license)
   
## Technologies
- Backend: Java, Spring Boot, Maven
- Frontend: Angular, TypeScript
- Database: MySQL
  
## Installation

1. Clone this repository to your local machine.
2. Make sure you have Java and Node.js installed.
3. Install the required dependencies for both backend and frontend.

## Backend Setup

1. Open the backend directory in your preferred IDE.
2. Configure the database connection in the application.properties file.
3. Build and run the Spring Boot application.

## Frontend Setup

1. Open the frontend directory in your terminal or command prompt.
2. Run the following command to install the required dependencies:
    ```
    npm install
    ```
3. Start the Angular development server:
    ```
    ng serve
    ```
## Usage

1. Access the application in your web browser at *http://localhost:4200*.
2. To retrieve the current exchange rate of a currency, enter the currency code and your name in the input fields and click the **Send** button.
3. The result will be displayed on the screen.
4. To view the history of previous currency requests, click the **Display data** button. The list of past requests will be displayed.

## API Reference

### Get current currency value and save your request

```
  POST /currencies/get-current-currency-value-command
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `currency` | `string` | **Required**. Currency code |
| `name` | `string` | **Required**. Your name |

##### Request

```json
{
    "currency": "GBP",
    "name": "Jan Kowal"
}
```

##### Response

```json
{
    "value": 5.1729
}
```

### Get all requests

```
  GET /currencies/requests
```

##### Response

```json
[
    {
        "currency": "GBP",
        "name": "Jan Kowal",
        "date": "2023-08-03T21:01:11.556553",
        "value": 5.1729
    }
]
```

## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/).
