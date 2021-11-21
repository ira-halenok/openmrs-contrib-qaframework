Feature: Vitals Management

  Background:
    Given a user clicks on Capture Vitals link from Patient dashboard
    Then the system loads Vitals page

  @selenium
  @vitals
  Scenario: Normal vitals
    When a user enters normal patient vitals
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table 

  @selenium
  @vitals 
  Scenario: Height below minimum value
    When a user enters a "height" "5"
    Then the system shows warning message "Minimum: 10"

  @selenium
  @vitals 
  Scenario: Height above maximum value
    When a user enters a "height" "300"
    Then the system shows warning message "Maximum: 272"

  @selenium
  @vitals 
  Scenario: Weight below minimum value
    When a user enters a "weight" "-5"
    Then the system shows warning message "Minimum: 0"

  @selenium
  @vitals 
  Scenario: Weight above maximum value
    When a user enters a "weight" "260"
    Then the system shows warning message "Maximum: 250"

  @selenium
  @vitals 
  Scenario: Temperature below minimum value
    When a user enters a "temperature" "12"
    Then the system shows warning message "Minimum: 25"

  @selenium
  @vitals 
  Scenario: Temperature above maximum value
    When a user enters a "temperature" "44"
    Then the system shows warning message "Maximum: 43"

  @selenium
  @vitals 
  Scenario: Pulse below minimum value
    When a user enters a "pulse" "-10"
    Then the system shows warning message "Minimum: 0"

  @selenium
  @vitals 
  Scenario: Pulse above maximum value
    When a user enters a "pulse" "290"
    Then the system shows warning message "Maximum: 230"

  @selenium
  @vitals 
  Scenario: Respiratory rate below minimum value
    When a user enters a "respiratory rate" "-20"
    Then the system shows warning message "Minimum: 0"

  @selenium
  @vitals 
  Scenario: Respiratory rate above maximum value
    When a user enters a "respiratory rate" "120"
    Then the system shows warning message "Maximum: 99"

  @selenium
  @vitals 
  Scenario: Systolic blood pressure below minimum value
    When a user enters a "systolic blood pressure" "25"
    Then the system shows warning message "Minimum: 50"

  @selenium
  @vitals 
  Scenario: Systolic blood pressure above maximum value
    When a user enters a "systolic blood pressure" "400"
    Then the system shows warning message "Maximum: 250"

  @selenium
  @vitals 
  Scenario: Diastolic blood pressure below minimum value
    When a user enters a "diastolic blood pressure" "10"
    Then the system shows warning message "Minimum: 30"

  @selenium
  @vitals 
  Scenario: Diastolic blood pressure above maximum value
    When a user enters a "diastolic blood pressure" "180"
    Then the system shows warning message "Maximum: 150"

  @selenium
  @vitals 
  Scenario: Saturation below minimum value
    When a user enters a "saturation" "-10"
    Then the system shows warning message "Minimum: 0"

  @selenium
  @vitals 
  Scenario: Saturation above maximum value
    When a user enters a "saturation" "120"
    Then the system shows warning message "Maximum: 100"

  @selenium
  @vitals
  Scenario: Normal vitals (low value boundary)
    When a user enters normal patient vitals (low value boundary)
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table

  @selenium
  @vitals
  Scenario: Normal vitals (high value boundary)
    When a user enters normal patient vitals (high value boundary)
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table 

  @selenium
  @vitals 
  Scenario: Height below minimum value (low value boundary)
    When a user enters a "height" "9"
    Then the system shows warning message "Minimum: 10"
