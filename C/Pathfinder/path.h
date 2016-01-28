
typedef struct{
	int indexes;
	int *item;
	int positions;
	int successes;
} Path;

void PathInit(Path *P, int size);

int PathAddEntry(Path *P, int entry);

int PathIncludesEntry(Path *P, int entry);

int PathRemoveEntry(Path *P);

void PathAddSuccesses(Path *P);

int PathGetSuccesses(Path *P);

void PathPrint(Path *P);

void PathKill(Path *P);