# SYSC-3110-project-Group-1

## Milestone 4

### Design
![](.github/mvc.png)

The design of our Plants vs Zombies game is architected after the modern MVC approach.
In this design architecture the `Controller` is the component in-between the `View` and the `Model`s.
Being the component in the middle, the controller is able to orchestrate changes to the model from user actions on the view.
The controller then has the ability to tell the view how to update itself and what data models to update on.

An example of this flow can be seen when a user clicks on a plant.
1. The `GameView` on initialization has set the `GameController` as the action listener for the buttons.
1. The `GameViewController` receives the `ActionEvent` and acts on it.
1. In the case of a plant it will ask the user for more inputs for the location of the plant.
1. Afterward the controller updates the model with the new plant on the `Game`'s grid.
1. The controller is now able to tell the view to rerender with the new game grid.

## User-visible change

Add three levels to the game and add three button to select the levels, also add a save and a load button to save and load the
game.

## UML and SEQ diagram change

added the save state class and the test of save state and game view controll to the uml diagram. added the save
state to the SEQ diagram.

## Issues

(May not be an Issues)The level of the game will increase while killing a zombie, and stay at the highest level, and the zombie
will increase unlimited.

## Milestone 3

### User-visible change

Add redo and undo button for the Button in the game GUI, so that users can repeat the previous step, previous and previous step. And also add several new game models in the game, includes nuts, double-peashooter, potato bomb for the plants and cone-head zombie for the zombies. So, we have five kinds of plants and two kinds of zombies in the game.

### UML and SEQ diagram change

Update the uml diagram with new models and add more method in the game class and game controller class so that the diagram match with the undo and redo button. Add several life lines for new models and update the game and game controller life lines for the sequence diagram.

### Issues

When the zombie died, new zombie not always appear in the columns 10. Instead, sometimes reappear in column 10, sometimes in column 9.

### Roadmap
Next milestone, we will add more level of difficulties for the game for users. Second, we will be add the save and load function for the game so that users save the game and continue the game next time just need to load the game file.


## MileStone 2

### User-visible change

Add JUnit test for all the model, at this version pvz game will be in the GUI instead of text based. Therefore, need to add the game view and game controller and we modify the game class. For the game view class, we add three images to represent peashooter, sunflower and zombies and create a 5*10[] button to model the backyard, another three buttons at bottom are for placing sunflower, peashooter, and skip turn. When user press the sunflower or peashooter, dialogue will show to user to input rows and columns. After they input, the backyard will add a image of plants at user's input location. At top of window there is a JMenu called option, users can restart or directly exit the game. (further will be able to choose difficulty and save)
All the logic is inherited from milestone1, just render the milestoen1 console text output into the GUI.

### UML diagram change
Delete class bullet and add class gameview and gameview controller so that game is in MVC format. Update the sequence by adding two lifeline for gameview and game controller associated to other class.


### Issues
Temporary our plants is placed by user inputing rows and columns but we want to change to user could place the plants through click the plants button and then click the one button in the backyard so that plants can directly place to the location which users clicked.


### Roadmap
Next milestone, we will add more models for the plants and zombies. Also we will add different difficulty for the users. And fix the issue mentioned before.

## MileStone 1

There are two packages in the project, one package for all the model(later will be more kinds of plants and zombies). The model package first create a abstract class called model to connects different kinds of model and other model can inherit from the model. Then creates two class abstractplant and abstractzombie inherited from model each class has its special parameter for zombie is speed and plant is cost.
Finally, creates three model: fast zombie, sun flower, peashooter. Fast zombie inherited from abstarctzombie and other two inherited from abstractplant. We also create a bullet to model the bullet shoot from peashooter.

game and grid in the other package. grid have the toString() method to print the temporary grid. And game class allow users to run the game
and have method to determine when the game is over.

## Authors

Xinyu Chen 10101031031

Weihong Shen 100980200

Jacky Chiu 101001982

Kirin 101003372


