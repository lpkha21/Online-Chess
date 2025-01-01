Online Chess is a web-based platform that enables users to play chess games online.
Features:
    Real-Time Gameplay: Engage in live chess matches with opponents over the internet.
    User Authentication: Secure login system to manage player accounts.
    Game History: Track past games and review moves.
    Responsive Design: Optimized for various devices, ensuring a seamless experience on desktops, tablets, and smartphones.

Installation
To set up the project locally:
1. Clone the repository.
2. Navigate to the project directory.
3. Install dependencies: Ensure you have Maven installed, then run:
           mvn install
4. Set up the database:
   Import the Accounts.sql file to set up the necessary tables:
   mysql -u your_username -p online_chess < Accounts.sql
5. Configure database connection:
   Update the src/main/resources/application.properties file with your database credentials:
       spring.datasource.url=jdbc:mysql://localhost:3306/online_chess
       spring.datasource.username=your_username
       spring.datasource.password=your_password
6. Run the application
     mvn spring-boot:run

USAGE:
  Access the application by navigating to http://localhost:8080 in your web browser.
