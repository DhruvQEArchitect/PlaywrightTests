Feature: To test integration of playwright api with cucumber

  @api
  Scenario Outline: To verify address API
    Given User executes address API <endpoint>
    When User fetches data
    Then Address data should not be empty

    Examples:
      | endpoint                                  |
      | https://dummyjson.com/products/categories |
