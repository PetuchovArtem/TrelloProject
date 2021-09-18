# language: en
@Smoke
Feature: Work with board and boards Items
  Scenario: Create new Board

    When a user is logged in Trello
    And user is creating a board with name
    Then board is created

  Scenario: Create new List with new Card

    When a user is logged in Trello
    And user is creating a new list
    And user is creating a new card
    Then card is created

    Scenario:  Move created card
      When a user is logged in Trello
      And user is creating a second list
      And user is moving card in new list
      Then card is moved

  Scenario:  Delete board by id
    When a user is logged in Trello
    And user is deleting last board
    Then board is deleted