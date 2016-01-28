#include <stdlib.h>
#include "path.c"
#include "vector.c"

/*	Function AllPathsRec is a recusive algorithm that locates the solution 
	to the given Vector. 
	
	Parameters:
		int position: The current postion within the Vector.
		Vector *V: The Vector which the a solution will be found for.
		Path *Solution: The Path postion of Vectors are stored.
		int size: The size of the vector.
*/
void AllPathsRec(int position, Vector *V, Path *Solution, int size){
	// Base case
	// Returns if current postion is; over the size of the Vector, less than 0 or
	// has already been added to the Solution Path.
	if(position > size || position < 0 || PathIncludesEntry(Solution, position)){
		return;
	}
	// If a solution is found.
	else if(position == size){
		// Add current position for pritning purposes.
		PathAddEntry(Solution, position);
		// Print solution path
		PathPrint(Solution);
		// Clear current position for solution path
		PathRemoveEntry(Solution);
		// Increment numbers of successes found for this vector
		PathAddSuccesses(Solution);
	}
	else if(PathAddEntry(Solution, position)){
		// Check Left
		AllPathsRec(position - V->item[position], V, Solution, size);
		// Check Right
		AllPathsRec(position + V->item[position], V, Solution, size);
		// Clear current entry
		PathRemoveEntry(Solution);
	}
}

/*	Function PathCheckNoPaths checks whether the given Solution Path 		has at least one solution. If not prints an appropriate message to 		the user.
	Parameters:
		Path *Solution: The solution Path to be checked.
*/
void PathCheckNoPaths(Path *Solution){
	if(PathGetSuccesses(Solution) == 0){
		printf("No Solution!\n");
	}
}

int main(){
	Vector *v = (Vector*) malloc(sizeof(Vector));
	VectorRead(v);
	Path *solution = (Path*) malloc(sizeof(Path));
	PathInit(solution, v->size);
	AllPathsRec(0, v, solution, solution->indexes);
	PathCheckNoPaths(solution);
	PathKill(solution);
	VectorKill(v);
}
