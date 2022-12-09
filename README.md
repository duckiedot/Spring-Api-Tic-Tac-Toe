# Project Description: 
The goal of this task is to implement an API which can be used to play the well known game Tic Tac Toe.
The project consits of spring secuirty and bearer API authentication.

# Techstack used
- Java 17
- Junit 4.13.1
- Spring Boot 2.7.4
- Gradle 7.5
- MySQL

# Game Acceptance criteria:
1. There are 2 players
2. Every player is represented by a unique symbol, usually it's X and O
3. The board consists of a 3x3 matrix. A player wins if they can align 3 of their markers in a vertical, horizontal or diagonal line
4. Player can join more than one game
5. Player can track number of wins and total games played

# Prequasitories
- Create MySql database
- Update databse info in resources/application.properties

 # API collection: 
- Downloand this postman collection - > <a id="raw-url" href="https://raw.githubusercontent.com/duckiedot/Spring-Api-Tic-Tac-Toe/blob/master/tictactoe.postman_collection.json">Download FILE</a>
  - POST
    - Register Player: 
      - username: string
      - password: string
    - Login Player
      - username: string
      - password: string
      - returns: JWT
    - Create Game
      - Authorization: Bearer {JWT}
      - returns: gameId
    - Join Game By Id
      - gameId: int
      - Authrization: Bearer {JWT}
      - returns: success/error
    - Make Move 
      - gameId: int
      - fieldId: int
      - Authrization: Bearer {JWT}
      - returns: win/sucess/error
  - GET
    - Display board
      - pathVariable: int gameId
      - returns: formated board

# How to play the game
- Register two players
- Login with username and pass, save returned JWT
- Create the game with JWT, remember returned gameId
- Join the game with the gameId and JWT of second player
- Make move with the first player
- Make move with another player and proceed switching players till the game is finished
- At any time you can display the board
