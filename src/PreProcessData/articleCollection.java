package PreProcessData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.LineListener;

import Classes.Path;

/**
 * This is for INFSCI 2140 in 2017
 *
 */
public class articleCollection implements DocumentCollection {
	//you can add essential private methods or variables
	private String filepath = Path.articleDir;
    private FileReader reader = null;
    private String id = null;
    private BufferedReader br = null;
    private String line = null;
    
    
	// YOU SHOULD IMPLEMENT THIS METHOD
	public articleCollection() throws IOException {
		// This constructor should open the file in Path.DataWebDir
		// and also should make preparation for function nextDocument()
		// you cannot load the whole corpus into memory here!!
		reader = new FileReader(filepath);
		br = new BufferedReader(reader);
	}
	
	// YOU SHOULD IMPLEMENT THIS METHOD
	public Map<String, Object> nextDocument() throws IOException {
		// this method should load one document from the corpus, and return this document's number and content.
		// the returned document should never be returned again.
		// when no document left, return null
		// NT: the returned content of the document should be cleaned, all html tags should be removed.
		// NTT: remember to close the file that you opened, when you do not use it any more
		while((line = br.readLine()) != null){
			String url_link = line;
			line = br.readLine();
			String content = line;
			content = content.replaceAll("[\\pP‘'“”]","");		//remove the punctuation
			content = content.replaceAll("\\d+","");//remove the number
			content = content.replaceAll("/$","");
			Map<String, Object> doc = new HashMap<String, Object>();
			doc.put(url_link, content);
			return doc;
			
		}
		br.close();
		reader.close();
		return null;
	}
	
}
