package PreProcessData;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.HashSet;

import javax.swing.text.AbstractDocument.BranchElement;

import Classes.Path;

public class StopWordRemover {
	//you can add essential private methods or variables in 2017.
	private HashSet<String> stopword = new HashSet <String> ();  
	public StopWordRemover( ) {
		// load and store the stop words from the fileinputstream with appropriate data structure
		// that you believe is suitable for matching stop words.
		// address of stopword.txt should be Path.StopwordDir
		String stoppath = Path.StopwordDir;

		try {
			FileReader reader = new FileReader(stoppath);
			BufferedReader br = new BufferedReader(reader);
			String word = null;
			while((word = br.readLine()) != null){
				stopword.add(word);
			}
			
			br.close();
	        reader.close();
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// YOU MUST IMPLEMENT THIS METHOD
	public boolean isStopword( String word ) {
		// return true if the input word is a stopword, or false if not
		if(stopword.contains(word)){
			return true;
		}
		return false;
	}
}
