## My solution for Problem 1: Bibliography Generator

This repository contains the solution for Problem 1 from Lecture 8 of the Northeastern University course CS 2510 (Fundamentals II), Spring 2022 semester. Find it at: https://course.ccs.neu.edu/cs2510sp22/lecture8.html


---

### Problem Description

The problem requires designing a program to create a formatted bibliography for a research paper. The bibliography is made up of two types of documents: **books** and **Wikipedia articles**.

* Each document has an **author** and a **title**.
* Each document can also refer to a bibliography of other documents.
* **Books** have an additional field for the **publisher**.
* **Wikipedia articles** have an additional field for a **URL**.

The goal is to produce a formatted bibliography that meets the following criteria:

* It should only include **books**.
* Each entry must be formatted as: `"Last name, First name. "Title".". `
* The entries must be sorted alphabetically by the author's last name.
* Duplicate entries, defined as having the same author and title, should be removed.

---

##### How To Run
- Clone this repository.
- Open the project in the Eclipse IDE.
- The main logic and tests are contained in lab1/src/IDocument.java
- You will need to follow the instructions at: https://course.ccs.neu.edu/cs2510sp22/lab1.html, especially you will need to download the tester.jar and javalib.jar from there and add them to the Eclipse project classpath, and follow the "Set up the run configuration and run the program" part.
