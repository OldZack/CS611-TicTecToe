Name: Wanzhi(Zack) Wang
Email: z1wang@bu.edu
BUID: U72807790

Execution instructions: 
1. Run MainGame.java file.
2. Enter the game pick menu, type in 1 or 2 to play Tic Tac Toe or Order and Chaos. Type 0 to exit the program.
3. In each game's main menu, type in 1 to start a new game, if user has not done any setting, the new game runs based
   on default setting (two players, 3*3 board for game TTT).
4. Type 2 to change game settings, in TTT user can set the board size and player team size, while in OAC users can set
   player team size. (ps. change team size will resect the scoreboard.)
5. Type 3 to print the scoreboard that contains the score of each player as well as the whole team (one for each win).
6. Type 4 to return to the last menu.
7. When a new game starts, following the instruction printed on screen, type in the slot number/symbol to place a chess
   on specified position. When the winning condition is met, the program will print out a notification and return to the
   game's main menu.

Description of class:
Marker:   A class that defines variables for a cell to contain. For TTT and OAC, the cell only needs one variable.
Cell:     A lass that defines a cell that represents a single grid on the game board, each cell has a symbol that represents
          what will be shown on screen.
Board:    A class generates a squire game board for chess games. It can be printed and modified.
Player:   A class that defines a player variable who has a name and keeps its personal score.
Team:     A class that defines a team that consists multiple players. Each team has a variable that keeps track of
          the score that the whole team wins.
Game:     An abstract class that defines the basic elements for a board game, including game's name, game board and player teams.
OAC:      A class that defines a game called Order and Chaos.
TTT:      A class that defines a game called Tic Tac Toe
MainGame: The main class that generates a menu for users to pick the game they want to play.