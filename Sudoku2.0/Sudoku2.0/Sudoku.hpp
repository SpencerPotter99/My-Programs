
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
    bool solution(int, int);
    bool valCheck (int, int, int);
    void printGrid();
    void setVal (int, int, int);
    int getValue(int, int);
// DECLARE OTHER FUNCTIONs YOU MAY NEED

private:
    int grid[9][9];
    int originalGrid[9][9];
    bool solvedchecker;
    // DECLARE other variables you will need

};

// All function bodies go from here on.

std::istream &operator>>(std::istream &input, Sudoku &s)
{
    // OVERLOADING the operator >> so we can seablessly read the file
    // Please provide code to populate grid from the board
    char lineNum[9][9];
    char getNum;
    int x;
    ifstream inputSheets;
    
    if (!input){
        cout << "error opening file" << endl;
        exit (0);
    }
    
    //function that reads the file and puts the numbers into a array to make the sudoku board
    
    for (int r = 0; r<= 8; r++){
        for (int c = 0; c<= 8; c++){
            input >> lineNum[r][c];
            getNum = lineNum[r][c];
            x = getNum - '0';
            s.setVal(r, c, x);
        }
    }
    
    //s.printGrid();
    s.solve();
    
    return input;
}

int Sudoku::getValue(int i, int j){
    cout << grid[i][j] << " ";
    if(j==9){
        cout << endl;
    }
    
    return grid[i][j];
}

bool Sudoku::solve(){
    
    solution(0,0);
    if(solvedchecker == true){
        cout << "the solution is :" << endl;
        printGrid();
        return true;
    }
    else{
        cout << "no solution found :(" << endl;
        return false;
    }
    
}

bool Sudoku::solution(int row, int col){
    
    //check to see if we got to the end of the board
    if (row == 8 && col == 9){
        solvedchecker = true;
        return true;
    }
    
    //moves the row up another level
    if (col > 8){
        row++;
        col = 0;
    }
    
    //checks to see if the grid already has a value
    if(grid[row][col] != 0){
       return solution(row, col + 1);
    }
    
    //the main backtracking function
    for (int i = 0; i <= 9; i++){
        
        //checks the number i to see if it is a solution
        if(valCheck(row, col, i)){
            grid[row][col] = i;
            //printGrid();
            
            if(originalGrid[row][col] != 0){
                grid[row][col] = originalGrid[row][col];
                return false;
            }
            
            if(solution(row, col + 1)){
                return true;
            }
            
            //if the top if statement doesnt work this will change the last solution back to 0
            //grid[row][col] = 0;
        }
        grid[row][col] = 0;
    }
    return false;
}

bool Sudoku::valCheck(int row, int col, int a){
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


void Sudoku::setVal (int row, int col, int num){
    grid[row][col] = num;
    originalGrid[row][col] = num;
}

void Sudoku::printGrid(){
    for (int r = 0; r < 9; r++){
        for (int c = 0; c < 9; c++){
            cout << grid[r][c] << " ";
        }
        cout << endl;
    }
    
    cout << endl;
    cout << endl;
}

#endif
