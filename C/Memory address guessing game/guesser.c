#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TOTAL_LOOPS 0
#define UNIQUES_GENERATED 1
#define NO_HINTS 2
#define TWO_GUESSES_PLUS 3
#define ONE_PLUS_HINTS 4

typedef struct {
	int real_part;
	int imag_part;
} complex ;

/*	Function getWidth returns the appropriate width value based on 
	the given command line entries. If entry provided is a valid number
	return that number otherwise 5.
	Parameters: 
		int noEntries: Number of entries given.
		char* entries[]: Array of entries given;
	Return:
	 	int : Appropriate width. */

int getWidth(int noEntries, char* entries[]){
	if(noEntries > 1 && validNumber(entries[1])){
		 return atoi(entries[1]);
	}
	else{
		return 5;
	}
}

/*	Function generateMatrix generates a 2D N x N matrix  of type complex
	with N defined by the inputed width.
	Parameters:
		int width: Width of the matrix.
	Return:
		complex** : N x N matrix of type complex. */

complex** generateMatrix(int width){
	complex **M;
	// Generate width number of rows complex arrays.
	M = (complex**) malloc(width * sizeof(complex));
	int i;
	for(i =0; i < width; i++){
		// Generate width number of complex arrays within row.
		M[i] = (complex *) malloc(sizeof(complex) * width);
		int j;
		for(j =0; j < width; j++){
			M[i][j].real_part = i;
			M[i][j].imag_part = j;
		}
	}
	return M;
}

/*	Function validNumber checks if the provided string is a number.
	Parameters:
		char* entry: The pointer to the string to be checked.
	Return:
		int: 0 if inputted string is not a number, 1 if it is. */

int validNumber(char *entry){
	while(*entry != '\0'){ // iterate over  string
		if(isalpha(*entry)){ // check is current character is a latter
			return 0; 	// return 0 if string contains a letter
		}
		entry++;	
	}
	return 1;	// return 1 if string does not contain any letters.
}

/* Function killMatrix frees the memorry associated with a 2D N x N
	matrix of type complex where N is the specified width.
	Parameters:
		complex** M: Pointer the the 2D complex array.
		int width; The width of the 2D array; */

void killMatrix(complex** M, int width){
	int i;
	for(i = 0; i < width; i++){ // free width number rows.
		free(M[i]);
	}
	free(M); // free rest of matrix.
}

/* Function printHintH prints memory addres of the first column of
	the specified 2D complex matrix.
	Example output, 5 width 2D matrix.

	M[0][0] P00
	M[1][0] P10
	M[2][0] P20
	M[3][0] P30
  	M[4][0] P40
	
	Parameters:
		complex** M: 2D complex matrix which hints will be generated upon.
		int width: Width of the 2D matrix. */

void printHintH(complex** M, int width){
	int i;
	for(i =0; i < width; i++){ 	// Print of addresses of width number of 
								// rows of the matrix.
		printf("M[%d][0] %p\n", i, &M[i][0] );
	}
	printf("\n");
}

/* 	Function printHintHH prints memory addres of the first column of
	and row of the specified 2D complex matrix.
	Example output, 5 width 2D matrix.

	      	M[0][0]   M[0][1]   M[0][2]   M[0][3]   M[0][4]   
	M[0][0] P00       P01       P02       P03       P04
	M[1][0] P10
	M[2][0] P20
	M[3][0] P30
	M[4][0] P40
	
	Parameters:
		complex** M: 2D complex matrix which hints will be generated upon.
		int width: Width of the 2D matrix. */

void printHintHH(complex** M, int width){
	int i;
	printf("\t");
	for(i =0; i < width; i++){ 	// Print width number of columns
		printf("M[0][%d]   ", i);
	}
	printf("\nM[0][0] ");	
	for(i =0; i < width; i++){
		printf("%p ", &M[0][i] );
	}
	printf("\n");	
	for(i =1; i < width; i++){
		printf("M[%d][0] %p\n", i, &M[i][0] );
	}
	printf("\n");
}


/* 	Function printHintHH prints memory addres all of the columns 
	and rows of the specified 2D complex matrix.
	Example output, 5 width 2D matrix.

	      M[0][0]   M[0][1]   M[0][2]   M[0][3]   M[0][4]   
	M[0][0] P00       P01       P02       P03       P04
	M[1][0] P10
	M[2][0] P20
	M[3][0] P30
	M[4][0] P40
	
	Parameters:
		complex** M: 2D complex matrix which hints will be generated upon.
		int width: Width of the 2D matrix. */

void printHintHHH(complex** M, int width){
	int i;
	printf("\t");
	for(i =0; i < width; i++){ printf("M[0][%d]   ", i);}
	printf("\n");
	for(i =0; i < width; i++){ 
		printf("M[%d][0] ", i);
		int j;
		for(j=0; j < width; j++){
			printf("%p ", &M[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

/*	Function addUnique checks already generated complex numbers in the
	given list to determine whether the given row, column pair (i, j)
	is unique.
	Parameters:
		complex uniq[]: Array of type complex containing arrays which contains
						row column pairs that have already been generated.
		int i: 	Row number to be checked.
		int j:	Column number to be checked.
		int stats[]:	Array of stats of the game. The number of unique numbers
						generated is stored in index 1.
	Return:
		int:	Returns 0 if row column pair has already been generated and 
				returns 1 if row colum pair is unique.	*/
				

int addUnique(complex unq[], int i, int j, int stats[]){
	int x;
	for(x=0; x < stats[UNIQUES_GENERATED]; x++){
		// Unique pairs are already accounted for so only iterate over
		// already generated pairs.
		if(unq[x].real_part == i && unq[x].imag_part == j){
			return 0;
		}
	}
	// Add latest row column pair to array of already found pairs.
	unq[stats[UNIQUES_GENERATED]].real_part = i;
	unq[stats[UNIQUES_GENERATED]].imag_part = j;
	return 1;
}

/*	Function checkAnswer determines if user has quit, and if not
	checks if users answer is corrrect.
	Parameters:
		char* choice: 	Pointer to user's answer.
		int i: Row number that is correct.
		int j: Column number that is correct.
	Return:
		int:	Returns 0 if user quits, else function checkGuess is called
				and 0 is returned if row column is guessed incorrectly
				1 if guessed correctly;	*/

int checkAnswer(char* choice, int i, int j){
	if(strncmp(choice,"Q", 1) != 0){
			return checkGuess(choice, i, j);
	}
	return 0;
}

/*	Function checkHints determines if user selected a type of hint.
	Calls appropriate function if user input matches the hint type.
	Parameters:
		char* choice:	Pointer array of user input.
		complex** M:	2D array of type complex.
		int width:		Width of 2D matrix.	*/

int checkHints(char* choice, complex** M, int width){
	if(strncmp(choice,"HHH", 3) == 0){
		printHintHHH(M, width); 
		return 1 ;
	}
	else if(strncmp(choice,"HH", 2) == 0){
		printHintHH(M, width);
		return 1 ;
	}
	else if(strncmp(choice,"H", 1) == 0){
		printHintH(M, width);
		return 1 ;
	}
	return 0;
}

/*	Function checkGuess checks the users input (guess) and determines if
	that input matches with the correct row column pairs(i, j).
	Parameters:
		char* guess:	The user inputted guess;
		int i:			The correct row to be checked.
		int j:			The correct column to be checked.
	Return:
		int:			Returns 0 if the inputted guess does not match the
						correct pair, 1 if match is made.	*/

int checkGuess(char* guess, int i, int j){
	char *token;
	int x;
	token = strsep(&guess, " "); // extract first input 
	if(validNumber(token) && atoi(token) == i){	// 	proceed is first input is
												//	correct
		token = strsep(&guess, "\n");//extract second input
		if(validNumber(token) && atoi(token) == j){
			printf("Right!\n");	// User is correct.
			return 1;		
		}
	}	
	printf("Wrong\n");	//	User is incorrect
	return 0;
}

/*	Function printStats prints the stats of the game after the user has
	quit.
	1. 	Total number of uniquely generated row column pairs.
	2.	Percent of correctly guessed pairs on the first time.
	3. 	Percent of correctly guessed pairs after 2 or more guesses
		without hints.
	4.	Percent of correctly guessed pairs after one or more hints.
	Parameters:
		int stats[]:	Array of stats accumulated over the course of the game.
		*/

void printStats(int stats[]){
	printf("-------------------------------------------\n");
	printf("%d	   Total number\n", stats[UNIQUES_GENERATED]);
	printf("%2.0f%%     Correct first time (no hints)\n",
	((double) stats[NO_HINTS] / stats[TOTAL_LOOPS]) * 100);
	printf("%2.0f%%     Correct after 2 or more guesses (no hints)\n",
	(double) stats[TWO_GUESSES_PLUS] / stats[TOTAL_LOOPS] * 100);
	printf("%2.0f%%      Correct after hint(s)\n", 
	(double) stats[NO_HINTS] / stats[TOTAL_LOOPS] * 100);
	printf("-------------------------------------------\n");
}

/*	Function setStats sets the total values of hint and guess related stats.
	Parameters:
		int *stats: Pointer to the array of total stats.
		int hints: Number of hints used in round of game.
		int guesses: Number of guessed used in round of game; */

void setStats(int *stats, int hints, int guesses){
	if(hints == 0 && guesses==1){ // No hints used and guessed on first try.
		stats[NO_HINTS] += 1;
	}
	if(hints ==0 && guesses >= 2){ // No hints used and 2 or more guesses used.
		stats[TWO_GUESSES_PLUS] += 1;
	}
	if(hints >= 1){// One or more hints used.
		stats[ONE_PLUS_HINTS] += 1;
	}
	stats[TOTAL_LOOPS] += 1;	// Increase total amount of pairs generated.
}

/*	Function setUnique records the total number of unique row column pair 
	generated.
	Parameters:
		complex arr[]: 	2D matrix of type complex.
		int i: 			Row value of be checked for uniqueness.
		int j: 			Column value of be checked for uniqueness.
		int stats[]: 	Array of total stats. Unique pairs generated is index 1.
		*/

void setUnique(complex arr[], int i, int j, int stats[]){
	stats[UNIQUES_GENERATED] += addUnique(arr, i, j, stats);
}

int main(int argc, char *argv[]){
	// Instantiate tallied variables.
	int i, j, width, correct, hintCount, guesses, hints, trys;
	//Instantiate stats array
	int stats[5]  = {0};
	// Determine width of Matrix	
	width = getWidth(argc, argv);	
	// Generate matrix based on width
	complex** M = generateMatrix(width);	
	// Create randmom number generator.
	srand((unsigned)time(NULL));	
	// Create string array for user input.
	char choice[20];	
	//	Create array of type complex to store generated row column pairs
	complex uniques[width * width];	
	// Continue game until user quits.
	while(strncmp(choice, "Q", 1) != 0){
		// Generate random row column pairs for ser to guess.
		i = rand()  % width;
		j = rand()  % width;
		// Reset counting variable for each generated row column pair.
		correct = hints = guesses = 0;
		while(correct == 0 && strncmp(choice, "Q", 1) != 0){
			// Count number of hints used for this specific pair.
			hintCount = 0;
			//	Display message to user displaying options.
			printf("M[0][0]=%p. M[i][j]=%p What's i and j?\n(Q to Quit or H or
			 HH or HHH for hints.): ", &M[0][0], &M[i][j]);
			// Recieve and store user input.
			fgets(choice, 20, stdin);
			// Check if user requested hints
			hintCount += checkHints(choice, M, width);
			// If user did not want hints check is valid answer submitted
			if (hintCount == 0){
				//	Check answer
				correct = checkAnswer(choice, i, j);
			}
			//	Accumulate hints used.
			hints += hintCount;
			// Accumulate guesses used.
			guesses++;
		}
		//	If user has not quit calculate stats for pair.
		if (strncmp(choice, "Q", 1) != 0){
			setUnique(uniques, i, j, stats);
			setStats(stats,hints, guesses);
		}
	}
	// Print stats of the game after user has quit.
	printStats(stats);
	// terminate matrix.
	killMatrix(M, width);
}
