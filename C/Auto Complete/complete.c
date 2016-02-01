// gcc -o complete complete.c trie.c

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "trie.h"

int MAX_CHARS = 24;

/*	Name: createTrie 

	Description: Creates a Trie tree of words from a given file.

	Parameters:
		Trie* T: A pointer to the Trie which words from the File will be placed in.
		FILE *F: File of words to be inserted into Trie.
*/
void createTrie(Trie *T, FILE *F){
	char current_word[MAX_CHARS];
	// While File still has words
	while( fgets(current_word, MAX_CHARS, F) != NULL){
		// insert each word into Trie.
		insert(T, current_word, strlen(current_word));
	}
}

/*	Name: promptUser 

	Description: Prompts user for input to be found within Trie.

	Parameters:
		Trie* T: A pointer to the Trie which autocomplete words will be found.
*/
void promptUser(Trie *T){
	char wordToFind[MAX_CHARS];
	// continues until user ends program.
	while(1){
		printf("Enter String: ");
		// get autocomplete word from standard input
		fgets(wordToFind, MAX_CHARS, stdin);
		// print autocomplete words
		printWords(T, wordToFind, (int) strlen(wordToFind));
	}
}


int main(){
	FILE *F = fopen("./american-english-no-accents", "r");
	Trie *T = initTrie();
	createTrie(T, F);
	fclose(F);
	promptUser(T);	
}