Feature: To Validate the login and add to kart functionality of Amazon

  @TestId1 @Demo
  Scenario: To validate the logout functionality of an application
    Given  User login and navigate to homepage of application
    When User clicks on Sign out Button
    Then Verify Whether the user signout succesfully

  @TestID2 @Demo
  Scenario: To Validate the links in Login Page
    Given User Navigate to Home Page and opens the hyperlinks
    When User iterate the available links in Login Page
    Then User Verify the Hyperlinks

  @TestID3 @Demo
  Scenario: To validate the Add-cart function
    Given  User login and navigate to homepage of application
    When user selects the product and add the product to kart
    Then verify the added product

  @TestID4 @Demo
  Scenario: To validate the Remove the product from cart function
    Given  User login and navigate to homepage of application
    When user selects the product and add the product to kart
    And User Removes the Product from the Kart
    Then Verify the removed Product

  @TestID5 @Demo
  Scenario: To Validation the Sort options of clothing Category
    Given User login and navigate to homepage of application
    When user navigates to the clothing session
    And User selects the product by sorting option
    Then Verfiy the sorted product by its value

  @TestID6 @Demo
  Scenario: To Validation the Filter options of clothing Category
    Given User login and navigate to homepage of application
    When user navigates to the clothing session
    And User selects the product by filter option
    Then Verfiy the filtered product is displayed

  @TestID7 @Demo
  Scenario: To Validation the Filter options of clothing Category
    Given User login and navigate to homepage of application
    When user navigates to the clothing session
    And User selects the product by filter option
    Then Verify the Page navigation
