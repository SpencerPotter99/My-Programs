//
//  main.cpp
//  Sudoku2.0
//
//  Created by Spencer Potter on 10/10/21.
//
#include "gtest.h"

#include "Sudoku.hpp"
#include <iostream>

int main(int argc, const char * argv[]) {
    std::ifstream puzzleIn("evil.txt");
    std::ifstream answerIn("easy.answer");
    Sudoku puzzle;
    Sudoku answer;

    puzzleIn >> puzzle;
    
    return 0;
}

