Feature: Search a flight

  Scenario: Search flight for Delhi to pune
    Given we are on website page
    Then select departure city
    Then select arrival city
    Then select date
    Then select Number of Passengers
    Then select currency
    Then click on search button
