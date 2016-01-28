typedef struct {
        int *item;
        int size;
} Vector;

void VectorRead(Vector *V);
int VectorGetInteger(Vector *V, int postion);
void VectorKill(Vector *V);
void VectorPrint(Vector *V);
