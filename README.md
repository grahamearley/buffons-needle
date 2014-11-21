# Buffon's Needle Simulator README #
Graham Earley, *CS257, Carleton College*

### Status of the program:
The program currently works as I had originally conceived. All desired features are
implemented and functional.

### Implementation of MVC:
- Model: the model stores the simulation information, such as the needles on
  the board, the intersections, and the approximation of pi.
- View(s): the program has two views:
    - The board view displays a board with the needles on it.
    - The number view displays text that tells the user how many
      needles are on the board, how many intersections there are,
      and, of course, the estimation of pi.
- Controller: the controller coordinates the updating of the model and views,
  taking input from the user where necessary (namely, for needle tosses).
      
### Core classes:
- Needle
    - contains position, length, and angle information
        - the position and angle information is percentage based:
          the values are completely relative, allowing the needles
          to be placed within any window.
    - creates the JavaFX Line object within a window.
- Model
    - stores needle positions and allows needles to be added
    - stores the dimensions of the board (for calculations)
    - stores number of slats (and calculates their positions based on
      the board width)
    - calculates number of intersections between needles and slats