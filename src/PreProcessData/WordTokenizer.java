package PreProcessData;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * This is for INFSCI 2140 in 2017 fall
 * 
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
	//you can add essential private methods or variables
	
	// YOU MUST IMPLEMENT THIS METHOD
	private ArrayList<String> wordlist = new ArrayList<>();
	private int countn = 0;
	public WordTokenizer( String content ) {
		char[] content_t = content.toCharArray();
		int k = 0;
		while(k<content_t.length)
		{
			int st = k;
			while(k<content_t.length && content_t[k]!=' ')
			{
				k++;
				//if(texts[k]== '-') break;
			}
			//get the word
			String tmp = new String(content_t, st, k-st);
			//if match the criteria, add the word into arraylist
			wordlist.add(tmp);
			//go to the start of next word
			while(k<content_t.length && content_t[k] ==' ')
			{
				k++;
			}
		}

	}
	
	// YOU MUST IMPLEMENT THIS METHOD
	public String nextWord() {
		// read and return the next word of the document
		// or return null if it is the end of the document
		if(countn == wordlist.size()){
			return null;
		}
		else{
			countn++;
			return (wordlist.get(countn-1));

		}
	}
	
}
