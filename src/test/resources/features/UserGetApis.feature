Feature: User GET APIs for Books

  Scenario: User retrieves all books successfully
    Given User sends a GET request to "/api/books"
    Then the user response status should be 200
    And the user response should contain "Book Title"

  Scenario: User retrieves a specific book by ID successfully
    Given User sends a GET request to "/api/books/1"
    Then the user response status should be 200
    And the user response should contain "Book Title"

  Scenario: User retrieves a non-existing book by ID
    Given User sends a GET request to "/api/books/999"
    Then the user response status should be 404
    And the user response should contain "Book not found"
