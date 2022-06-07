# ✨ Gold Collection Game ✨


[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

✨ Gold Collection Game a 4 player game on the game board. Each player represents an algorithm. ✨
## Project Achievements

- Greedy Algorithm, A* Algorithm
- Dominance of FIFO , LIFO , Linked List data struct structure
- OOP 
- Java Swing

> You can play on a board of any size you want. The rules of the game are as follows:

> There are hidden and non-hidden gold coins on the board.

> Player A: Uses the Greedy Algorithm.

> Player B: tries to go to the nearest big bottom

> Player C: moves like player b and reveals hidden gold.

> Player D: player A, b, c moves knowing the movements of the players.

>  Each player has a movement cost.

> Whoever finishes the game with the highest amount of gold wins.


The main goal in this game is to finish the game with the most gold.

![Game_Gif](/Example_Image/readmeImages/_game.gif)



## Tech

- Java programing Language
- Java Swing Framework


## Installation
This min requires [Java-8 SDK](https://www.oracle.com/tr/java/technologies/javase/javase8-archive-downloads.html)

![Game_Settings](/Example_Images/readmeImages/game_settings.PNG)

If you want, you can change the game board size, gold ratios, players' game features such as gold movement here.



```sh
   public void _file_Write_Passed_Rota_Player(Player _player, String _key){
        _file = new File(".\\Result_Player_"+_key+".txt");
        ....
        
```
Every step taken by the players at the end of the game is recorded in a file, you can change the extension of this file [here](/src/main/Board.java)

## License

MIT

**Free Software, Hell Yeah!**
