@AllCustomerData
  Feature:Test all customers' data

    Background: Api endpoint is being set in response
      Given user provides the api endpoint to set the response using "https://www.gmibank.com/api/tp-customers"

    Scenario: validate all customers' data
      Given manipulate all customers' data
      And user sets the data in correspondent file
      Then user validate date



