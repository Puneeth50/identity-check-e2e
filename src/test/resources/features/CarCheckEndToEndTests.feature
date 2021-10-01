@E2E
Feature: Free car check end to end scenario
  As a user,
  I should be able to see car details when searched with reg number

  Scenario: Full car check by entering registration number
    Given the car reg details are extracted from files in "input" directory
    And the expected car details are extracted from files in "output" directory
    When the user is on Car Check Home page
    Then compare vehicle reg search results with expected output data

