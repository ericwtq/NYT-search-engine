package PreProcessData;
import Classes.Stemmer;


/**
 * This is for INFSCI 2140 in 2017
 * 
 */
public class WordNormalizer {
	//you can add essential private methods or variables
	
	// YOU MUST IMPLEMENT THIS METHOD
	public String lowercase( String words ) {
		//transform the uppercase characters in the word to lowercase
		char [] chars = words.toCharArray();
		for(int i = 0;i < chars.length;i++){
			if(Character.isUpperCase(chars[i])){
				chars[i] = Character.toLowerCase(chars[i]);
			}
		}
		return String.valueOf(chars);
	}
	
	public String stem(String word)
	{
		//use the stemmer in Classes package to do the stemming on input word, and return the stemmed word
		char [] chars = word.toCharArray();
		String str="";
		Stemmer s = new Stemmer();
		s.add(chars,chars.length);
		s.stem();
		str = String.valueOf(s);
		return str;
	}
	
}
