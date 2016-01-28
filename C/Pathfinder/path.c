#include "path.h"
#include <stdlib.h>
#include <stdio.h>

/*	Function PathInit initializes a Path struct.
	Parameters:
		Path *P: Pointer to Path struct.
		int size: Size of Path to be set.
*/
void PathInit(Path *P, int size){
	P->item = malloc(size  * sizeof(int));
	P->indexes = size - 1;
	P->positions = 0;
	P->successes = 0;
}

/*	Function PathAddEntry adds the given entry to the given path.
	Parameters:
		Path *P: Pointer to Path.
		int entry: Integer to be added to Path.
*/
int PathAddEntry(Path *P, int entry ){
	if(entry < 0){ return 0;}
	if(PathIncludesEntry(P, entry) == 0){
		P->item[P->positions] = entry;
		P->positions++;
		return 1;
	}
	return 0;
}

/*	Function PathIncludesEntry searches and returns whether the given entry is 
	in the given Path;
	Parameters:
		Path *P: The Path to be searched.
		int entry: THe entry to be found;
	Return:
		int: Returns 0 if Path does not incude entry and 1 if 
			 Path does contain entry.
*/
int PathIncludesEntry(Path *P, int entry){
	int i;
	for (i =0; i < P->positions; i++){
		if( P->item[i] == entry){
			return 1;
		}
	}
	return 0;	
}

/*	Function PathRemoveEntry removes the top most entry of the given path. 
	Returns 0 if the position is now less than 0 or 1 elsewise.
	Parameters:
		Path *P: The given path.
	Return:
		int: 0 if Path's position value is less than 0.
			 1 if Path's position value is greater than 0.
*/
int PathRemoveEntry(Path *P ){
	P->positions--;
	if(P->positions < 0){
		return 0;
	}
	else{
		return 1;
	}
}

/*	Function PathAddSuccesses increments the success value of the given Path.
	Parameter:
		Path *P: The given Path.
*/
void PathAddSuccesses(Path *P){
	P->successes++;
}

/*	Function PathGetSuccesses returns the success value of the given Path.
	ParameterL
		Path *P: The given Path.
*/
int PathGetSuccesses(Path *P){
	return P->successes;
}

/*	The function PathPrint prints the stored positons of the given Path.
	Parameters:
		Path *P: The Path which will be printed.
*/
void PathPrint(Path *P){
	int i;
	for(i =0; i < P->positions; i++){
		printf("%d ",P->item[i]);
	}
	printf("\n");
}

/*	The function PathKill frees the given Path from memory.
	Parameters:
		Path *P: The Path to be freed.
*/
void PathKill(Path *P){
	free(P->item);
	free(P);
}