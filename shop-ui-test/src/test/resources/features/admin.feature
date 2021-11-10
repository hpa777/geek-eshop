Feature: Test Admin Feature

  Background: User is Logged In
    Given I open web browser
    When I navigate to login.html page
    And I provide textField with id "username" as "admin"
    And I provide textField with id "password" as "pass"
    And I click on element with id "btn-login"
    Then name should be "admin"

#    Examples:
#      | username | password | name |
#      | admin | pass | admin |

  Scenario: logout
    When I click on element with id "btn-logout"
    Then user logged out


  Scenario: add category
    Given I navigtate to category page
    When I click on element with id "newCategoryButton"
    And I provide textField with id "name" as "TEST_CATEGORY"
    And I click on element with id "submit"
    Then check category "TEST_CATEGORY"



