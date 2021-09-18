# language: en
@Regression
Feature: Search items by name

  Scenario: Fiend board by name

    When a user is logged in Trello
    And user is searching a board by name
    Then board is founded


  Scenario: Fiend card by name

    When a user is logged in Trello
    And user is searching a card by name
    Then card is founded