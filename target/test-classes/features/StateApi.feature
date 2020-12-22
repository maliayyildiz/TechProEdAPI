@AllStates
  Feature: read create update and delete all states

    Background: read all states
      Given user sets all states to response using "https://www.gmibank.com/api/tp-states"

    Scenario: read all states
      Given user manipulates all states
      And user saves the states to correspondent files
      Then user validate all states