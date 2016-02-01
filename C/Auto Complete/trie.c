#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trie.h"

int NUM_INDEXES = 54;

/*	Name: initTrie 

	Description: Creates a Trie node and initiaizes its instance variables.

	Return value:
		Trie*: A pointer to an initialized Trie node.
*/
Trie* initTrie(){
	Trie *T = (Trie*)malloc(sizeof(Trie));
	int i;
	for(i = 0; i < NUM_INDEXES; i++){
		T->level[i] = NULL;
	}
	T->leaf = NULL;
	return T;
}

/*	Name: initTrieLeaf 

	Description: Creates a TrieLeaf node and initiaizes its instance variables.

	Parameter:
		char* word: Word contained within TrieLeaf.

	Return value:
		TrieLeaf*: A pointer to an initialized TrieLeaf
*/
TrieLeaf* initTrieLeaf(char* word){
	TrieLeaf *TF = malloc(sizeof(TrieLeaf));
	strcpy(TF->word, word);
	return TF;
}

/*	Name: insert

	Description: Inserts the given word into the provided Trie.

	Parameters:
		Trie *T: A pointer to an Trie node where the given word will be inserted.
		char* word: The given word to be inserted.
		int length: The length of the given word.
*/
void insert(Trie *T, char* word, int length){
	int i, charIndex;
	Trie *current = T;
	// Iterate over length of word.
	for(i = 0; i < length; i++){
		// get index of letter at index of word
		charIndex = getCharIndex(word[i]);
		// if a trie node for this letter doesn't exist
		if(current->level[charIndex] == NULL){
			// create new Trie node
			current->level[charIndex] = initTrie();
		}
		// go to trie node of current letter
		current = current->level[charIndex];
	}
	// create TrieLeaf with word
	current->leaf = initTrieLeaf(word);
}

/*	Name: printWords

	Description: Traverses Trie by given word to desired node for printing.

	Parameters:
		Trie *T: Trie to be traversed.
		char* word: Word to traverse Trie by.
		int length: Length of word to traverse by.
*/
void printWords(Trie *T, char* word, int length){
	int i, charIndex;
	Trie *current = T;
	for(i = 0; i < length - 1 ; i++){
		if(current == NULL){
			return;
		}
		else{
			charIndex = getCharIndex(word[i]);
			if(current->level[charIndex] != NULL){
				current = current->level[charIndex];
			}
			else{
				return;
			}
		}
	}
	printAll(current);
}

/*	Name: printAll

	Description: Prints all words within the provided Trie node.

	Parameters:
		Trie *T: Trie node which words will be printed from.
*/
void printAll(Trie *T){
	if(T == NULL){
		return;
	}
	else{
		if(T->leaf != NULL){
			printf(" %s", T->leaf->word);
		}
		int i;
		for(i=0; i < 54; i++){
			if(T->level[i] != NULL){
				printAll(T->level[i]);
			}
		}
	}
}

/*	Name: getCharIndex

	Description: Given a letter, returns the appropriate index of that letter 
	within the Trie.

	Parameters:
		char c: Letter to be processed.

	Return value:
		int: Index of letter within Trie.
*/
int getCharIndex(char c){
	int charValue = (int) c;
	if (charValue >= 65 && charValue <= 90){
		return charValue - 63;
	}
	else if (charValue >= 97 && charValue <= 122){
		return charValue - 69;
	}
	else if (charValue == 39){
		return 1;
	}
	else{
		return 0;
	}
}