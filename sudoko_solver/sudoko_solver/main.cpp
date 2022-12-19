//
//  main.cpp
//  sudoko_solver
//
//  Created by Spencer Potter on 10/5/21.
//

#include <iostream>
#include "Sudoku.hpp"


int main(int argc, const char * argv[]) {

    std::ifstream puzzleIn("easy.txt");
    std::ifstream answerIn("easy.answer");
    Sudoku puzzle;
    Sudoku answer;
    

    puzzleIn >> puzzle;
    
    return 0;
}
