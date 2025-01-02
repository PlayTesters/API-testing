Feature: Library Book APIs

  Scenario: Admin successfully creates a new book
    Given Admin sends a POST request to "/api/books" with body
      """
      {
        "title": "Book Title",
        "author": "Author Name"
      }
      """
    Then the response status should be 201
    And the response should contain "Book Title"

  Scenario: Admin retrieves a book by ID
    Given Admin sends a GET request to "/api/books/1"
    Then the response status should be 200
    And the response should contain "Book Title"


  Scenario: Admin attempts to create a book with missing title
    Given Admin sends a POST request to "/api/books" with body
    """
    {
      "author": "Author Name"
    }
    """
    Then the response status should be 400 Bad Request
    And the response should contain "Invalid Input"

  Scenario: Admin attempts to create a book with missing author
    Given Admin sends a POST request to "/api/books" with body
      """
      {
        "title": "Book Title"
      }
      """
    Then the response status should be 400
    And the response should contain "Invalid Input"

  Scenario: Admin attempts to create a book with invalid data (empty string for title)
    Given Admin sends a POST request to "/api/books" with body
      """
      {
        "title": "",
        "author": "Author Name"
      }
      """
    Then the response status should be 400
    And the response should contain "Invalid Input"


