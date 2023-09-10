
# Used technologies
![JAVA 11](https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png =100x)
![SPRING 3](https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png =100x)
![Postgresql](https://user-images.githubusercontent.com/25181517/117208740-bfb78400-adf5-11eb-97bb-09072b6bedfc.png =100x)

# jwtBackend

his repository contains the source code for a JWT backend configuration that can be used to secure a Spring Boot application.

The configuration includes the following components:

    JwtAuthFilter: This filter validates JWT tokens in incoming requests.
    SecurityConfig: This class configures security for the application, including authentication and authorization rules.
    AuthController: This controller handles user authentication and registration.
    ProductController: This controller handles product-related operations, such as creating, retrieving, and deleting products.
    CloudinaryService: This class interacts with the Cloudinary service for image handling.
    UserMapper: This interface maps between User and UserDto objects.

To use the configuration, you will need to:

    Install the dependencies in the pom.xml file.
    Configure the security filters and rules in the SecurityConfig class.
    Use the AuthController for user authentication and registration.
    Use the ProductController for product-related operations.
    Use the CloudinaryService for image handling.

For more detailed information, please refer to the code and project repository.

Here are some of the key features of the JWT backend configuration:

    Uses JWT for authentication and authorization.
    Supports user registration and login.
    Allows for fine-grained authorization control.
    Manages product-related operations, such as creating, retrieving, and deleting products.
    Handles image handling using the Cloudinary service.

I hope this documentation is helpful. Please let me know if you have any questions.

Thank you!
