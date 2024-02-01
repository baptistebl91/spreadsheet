
# Simplified Spreadsheet Project

This Java project aims to create a basic spreadsheet program, featuring a 9x9 grid (9 columns indexed A to I and 9 rows indexed 1 to 9) capable of handling real numbers with a limited set of arithmetic operators. Inspired by Visicalc, the first spreadsheet program, this project focuses on essential spreadsheet functionalities.

## Project Overview

- **Objective:** To develop a functional and simplified spreadsheet application.
- **Development:** Built in Java, with the official API, and hosted on the department's Gitea server.

## Features

- **Grid Layout:** The spreadsheet consists of 81 cells, each referenced by a combination of column and row indices (e.g., B7).
- **Cell Formula:** Each cell contains a formula (initially empty) in prefix notation (e.g., + 2.66 * B7 0.33).
- **Cell States:** Cells can be empty, contain a correct and calculable formula, a correct but incalculable formula, or an incorrect formula, each visually distinct (empty: light gray, correct/calculable: green, correct/incalculable: yellow, incorrect: red).
- **Syntax Tree:** For efficiency, formulas are represented as an abstract syntax tree to avoid reinterpreting them repeatedly.
- **User Interaction:** Formulas are edited in a dedicated area and updated with the ↵ key, impacting the display of the spreadsheet.

## User Guidance

- **Terminal Usage:** It is advised to use the terminal for understanding various interactions and indications.
- **Syntax Adherence:** Strict adherence to the prefix notation and spacing between operators and operands is crucial for formula accuracy.

## Commands

- `make build`: Compiles the project.
- `make run`: Compiles and launches the program.
- `make clean`: Removes compiled classes in the build folder.
- `make jar`: Creates a `PetitTableur.jar` archive of the project.
- `make run-jar`: Executes the `PetitTableur.jar` archive.
