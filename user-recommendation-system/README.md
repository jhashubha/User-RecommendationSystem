Technologies Used
Java
Spring Boot
Maven

Build the application using Maven:
mvn clean install

After building the application, you can run it using the following command:
java -jar <jar file name>

Note: You can also run this application directly using Spring boot App(STS)  

## Testing the Endpoints

- Access the API endpoints using tools Postman:
- Base URL: `http://localhost:8080`


## Additional Notes

- Make sure to replace `your_database_name`, `your_username`, and `your_password` with your actual database details.




API Endpoints Details

1.User Endpoints:

GET /users/findByEmailId

Description: Retrieve a user by email ID.
Request Method: GET
Parameters:
email: The email ID of the user to retrieve.
:

POST /users/create
Description: Create a new user.
Request Method: POST
Request Body:
{
    "name": "shubham",
    "skillGroup": "JAVA",
    "yearsOfExperience": "5",
    "email": "shubham@gmail.com",
    "team": "DEVELOPER"
}

2. Review Endpoints:

POST /reviews/employee-review
Description: Submit a review for another user.
Request Method: POST
Request Body:
{
    "reviewerEmail": "shubham@gmail.com",
    "revieweeEmail": "abhi@gmail.com",
    "coworkers": true,
    "discription" : "Review Comments"    
}

GET /reviews/retrieve-all-reviews
Description: Retrieve all reviews.
Request Method: GET

