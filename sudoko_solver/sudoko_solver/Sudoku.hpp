
#ifndef SUDOKU
#define SUDOKU
#include <iostream>
#include <fstream>

using namespace std;

class Sudoku
{
public:
    Sudoku() {} // YOU MAY Modify The contructor if you wish
    bool solve();
    void printGrid();
    void setValue(int, int, int);
    bool isSafe(int, int, int);
    bool backtrack(int, int, int);
// DECLARE OTHER FUNCTIONs YOU MAY NEED
private:
    int grid[9][9];
    bool solution = false;
    // DECLARE other variables you will need

};

// All function bodies go from here on.

//sets the values for the sudoku grid
void Sudoku::setValue(int r, int c, int x) {
    grid[r][c] = x;
}


//prints the sudoku grid
void Sudoku::printGrid(){
    for (int r = 0; r <= 8; r++){
        cout << endl;
        for (int c = 0; c <= 8; c++){
            cout << grid[r][c] << " ";
        }
    }
    cout << endl;
    cout << endl;
}

//the function used for backtracking
bool Sudoku::backtrack (int row, int col, int n){
    //once the col reaches 10 the row goes up another level
    if (col == 10 ){
        row++;
        col = 0;
    }
    
    //checks to see if the grid already has a value
    if(grid[row][col] != 0){
        col++;
        backtrack(row , col, 0);
        
    }
    
    //the for loop for running the backtracking
    for (int i = 1; i <= 9; i++){
        
        //if is safe returns true then the grid is set to that number
        if (isSafe(row, col, i)){
            grid[row][col] = i;
            
            printGrid();
            //check = backtrack(row , col + 1, i) /*&& backtrack(row, col +1, i)) */ || check;
           //
            if(backtrack(row , col + 1, i)){
                
                backtrack(row , col + 1, i);
            }
            else{
               //backtracking if wrong
            grid[row][col] = 0;
            printGrid();
            }
        }
    }
    
    return false;
}

bool Sudoku::solve(){
    //this checks if there is a solution already found at the very beginning
    if (solution == true){
        return true;
    }

    
 
            backtrack(0, 0, 1);
    
    return true;
}


//check this later
bool Sudoku::isSafe(int row, int col, int a) {
    
    //checks the column
    for (int i=0; i < 9; i++){
        if(grid[row][i] == a){
            return false;
        }
    }
    
    //checks the row
    for (int x = 0; x < 9; x++){
        if(grid[x][col] == a){
            return false;
            }
        }
        
        //checks the top left grid
        if(row<= 2 && col <= 2){
            for(int g = 0; g <= 2; g++){
                for (int k = 0; k <= 2; k++){
                    if(grid[g][k] == a){
                        return false;
                    }
                }
            }
        }
    
    //checkks the top middle grid
    if(row <= 2 && col > 2 && col <= 5){
        for(int g = 0; g <= 2; g++){
            for (int k = 3; k <= 5; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    
    //checkks the top right grid
    if(row <= 2 && col > 5 && col <= 8){
        for(int g = 0; g <= 2; g++){
            for (int k = 6; k <= 8; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    
    //checkks the middle left grid
    if(row > 2 && row <= 5 && col <= 2){
        for(int g = 3; g <=5; g++){
            for (int k = 0; k <= 2; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    
    //checks middle grid
    if(row > 2 && row <= 5 && col > 2 && col <= 5){
        for(int g = 3; g <=5; g++){
            for (int k = 3; k <= 5; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    //checks the middle right grid
    if(row > 2 && row <= 5 && col > 5 && col <= 8){
        for(int g = 3; g <=5; g++){
            for (int k = 6; k <= 8; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    //checks the bottom left grid
    if(row > 5 && row <= 8 && col <= 2){
        for(int g = 6; g <=8; g++){
            for (int k = 0; k <= 2; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    
    //checks the bottom middle grid
    if(row > 5 && row <= 8 && col > 2 && col <= 5){
        for(int g = 6; g <=8; g++){
            for (int k = 3; k <= 5; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    
    //checks the bottom right grid
    if(row > 5 && row <= 8 && col > 5 && col <= 8){
        for(int g = 6; g <=8; g++){
            for (int k = 6; k <= 8; k++){
                if(grid[g][k] == a){
                    return false;
                }
            }
        }
    }
    
    return true;
}

std::istream &operator>>(std::istream &input, Sudoku &s){
    ifstream inputSheet;
    char lineNum [8][8];
    char getNumber;
    int x;
    // OVERLOADING the operator >> so we can seablessly read the file
    // Please provide code to populate grid from the board
    
    
    
    //input.read(lineNum, sizeof(lineNum)-1);
    
    if (!input) {
        cout << "error opening file" << endl;
        exit (0);
    }
    for (int r = 0; r <= 8; r++ ){
        for (int c = 0; c <= 8 ; c++ ){
            input >> lineNum[r][c];
            getNumber = lineNum[r][c];
            x = getNumber - '0';
            s.setValue(r, c, x);
        }
    }
    s.printGrid();
    s.solve();
    s.printGrid();
    return input;
}

#endif
