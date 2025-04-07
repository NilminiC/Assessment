Feature: Calculate the shipment quote
#  Test Case 1: Verify user can calculate the shipment quote from Malaysia  to India.
#  o Step 1: User go to https://pos.com.my/send/ratecalculator
#  o Step 2: User enter “Malaysia” as “From” country, and enter “35600” as the  postcode.
#  o Step 3: User enter “India” as “To” country, and leave the postcode empty.  o Step 4: User enter 1 as the “Weight”, and user press Calculate.
#  o Step 5: Verify user can see multiple quotes and shipments options  available.

  Background:
    Given I navigate to POS malaysia website

  Scenario Outline: verify user can calculate the shipment quote from Malaysia  to India.
    Given I am within rate calculator page
    Then I enter “<countryFrom>” and “<postcode>” from the dropdown
    And I enter "<countryTo>"  and empty postcode for the TO dropdown
    And I enter "<value>" weight and press on calculate button
    Then I verify that multiple quotes and shipments options are available

    Examples:
      | countryTo | postcode | value | countryFrom |
      | India     | 35600    | 1     | Malaysia    |

