# SYSC-3110-project-Group-1

MileStone 2

User-visible change 


Add JUnit test for all the model, at this version pvz game will be in the gui instead of text based. Therefore, need to add the gameview and game controller and we modfify the game class. For the gameview class, we add three images to represent peashooter, sunflower and zombies and create a 5*10[] button to model the backyard, another three buttons at bottom are for placing sunflower, peashooter, and skip turn. When user press the sunflower or peashooter, diaglogue will show to user to input rows and columns. After they input, the backyard will add a image of plants at user's input location. At top of window there is a JMenu called option, users can restart or directly exit the game. (further will be able to choose diffculty and save)
All the logic is inherited from milestone1, just render the milestoen1 console text output into the gui.

UML diagram change : delete class bullet and add class gameview and gameview controller so that game is in mvc format. Update the sequence by adding two lifeline for gameview and game comntroller assiocated to other class. 


Issues ：temporary our plants is placed by user inputing rows and columns but we want to change to user could place the plants through click the plants button and then click the one button in the backyard so that plants can directly place to the location which users clicked. 


Roadmap: next milestone, we will add more models for the plants and zombies. Also we will add different difficulty for the users. And fix the issue mentioned before.




MileStone 1

There are two packages in the project, one package for all the model(later will be more kinds of plants and zombies). The model pakcage first create a abstract class called model to connects different kinds of model and other model can inherit from the model. Then creates two class abstractplant and abstractzombie inherited from model each class has its speical parameter for zombie is speed and plant is cost.
Finally, creates three model: fast zombie, sun flower, peashooter. Fast zombie inherited from abstarctzombie and othe two inherited from abstractplant. We also create a bullet to model the bullet shoot from peashooter.

game and grid in the other package. grid have the tostring() method to print the temporary grid. And game class allow users to run the game 
and have method to deterine when the game is over.



Xinyu Chen 10101031031

Weihong Shen 100980200

Jacky Chiu 101001982

Kirin 101003372


