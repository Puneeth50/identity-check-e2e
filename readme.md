# Framework Tools and Libraries
* **BDD Test Specification**: Cucumber (Gherkin), structured English sentences
* **Main Programming Language**: Java
* **UI Automation**: Selenium-Webdriver
* **Supported Browsers**: chrome, firefox & IE
* **Build tool**: Maven
* **Utilities**: Lombok, SL4j (logging)
* **IDE**: Intellij
* **Assertions** : Hamcrest, Junit
* **Intellj Plugins**: Cucumber for Java, Gherkin & lombok

Browser is optional, which can be chrome, firefox. The default browser is Chrome.
Also, as in any Webdriver based test suite, you'll have to have [Chromedriver](https://sites.google.com/a/chromium.org/chromedriver/) and/or [Geckodriver](https://github.com/mozilla/geckodriver/releases) available in your PATH

# Page Object Model
PageObjects are used to store the WebElements for a Web Page. A good practice is to have a separate class for every single WebPage. To avoid duplication for multiple pages which have common web page elements a Parent class can be created and the child class can then inherit.
Every Page class extends "Base.class" to make use of the WebDriver Object and utility functions.

# Run Tests
TestRunner class is used to run a group of Tests which are tagged and represented in form of Feature files & Scenarios.

Location of runner class  - src/main/java/runner/TestRunner.java

Tags:
E2E - End to end tests

#NOTE:
when searched with BW57 BOW car reg mentioned in the input file the car check site is not returning any results.

# Report
HTML Report will be generated at /target/report/cucumber.html



	
