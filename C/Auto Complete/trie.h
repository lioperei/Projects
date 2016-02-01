
typedef struct TrieLeaf{
	char word[24];
}TrieLeaf;

typedef struct Trie{
	struct Trie *level[54];
	TrieLeaf *leaf;
}Trie;

Trie* initTrie();
TrieLeaf* initTrieLeaf(char* word);
int getCharIndex(char c);
void insert(Trie *T, char* word, int length);
void printWords(Trie *T, char* word, int length);
void printAll(Trie *T);