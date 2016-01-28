#include "vector.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/*	Function VectorRead reads a Vector from stdin and sets the given Vector
	equal to the input.
	Parameters: 
		Vector V: Vector to be set.
*/
void VectorRead(Vector *V){
	char size[20];
	fgets(size, 20, stdin);
	V->size = atoi(size);
	char item[V->size * 5];
	V->item = malloc(V->size * sizeof(int));
	fgets(item, V->size * 5, stdin);
	char *token;
	int i = 0;
	token =  strtok(item, " ");
	while(token != NULL){
		V->item[i] = atoi(token);
		token = strtok(NULL, " ");
		i++;
	}
}

/*	Function VectorGetInteger returns the integer value of the vector 
	at the given position.
	Parameters:
		Vector V: Vector containging integers.
		int position: Position of integer to be returned.

*/
int VectorGetInteger(Vector *V, int postion){
	return V->item[postion];
}

/*	Function VectorKill frees the given Vector from memory.
	Parameters:
		Vector V: Vector to be freed.
*/
void VectorKill(Vector *V){
	free(V->item);
	free(V);
}