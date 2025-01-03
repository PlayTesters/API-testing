Feature: GET APIs for Books


  Scenario: Retrieve all books successfully
    Given Admin sends a POST request to "/api/books" with body
      """
      {
        "title": "Book One",
        "author": "Author One"
      }
      """
    Then the response status should be 201
    And the response should contain "Book One"
    Given Admin sends a GET request to "/api/books"
    Then the response status should be 200
    And the response should contain "Book Title"

  Scenario: Retrieve a specific book by ID successfully
    Given Admin sends a GET request to "/api/books/1"
    Then the response status should be 200
    And the response should contain "Book Title"

  Scenario: Retrieve a non-existing book by ID
    Given Admin sends a GET request to "/api/books/999"
    Then the response status should be 404
    And the response should contain "Book not found"


