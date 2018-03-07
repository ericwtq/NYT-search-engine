package SearchLucene;



import java.io.BufferedReader;
import java.util.HashSet;
import java.io.FileReader;
import java.util.ArrayList;
import Classes.*;
import Classes.Query;


public class ExtractQuery {




	public Query ExtractQuery(String querycontent) {


		HashSet<String> stopword = new HashSet<String>();
		try{
			FileReader fr = new FileReader(Path.StopwordDir);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word = br.readLine())!=null){
				stopword.add(word);
			}
			br.close();
			fr.close();
		}catch(Exception e){

		}

		//remove stopword, stem and lowercase
		StringBuffer buf = new StringBuffer();
		Query q = new Query();
		String []content_l = querycontent.split(" ");
		for(int i=0;i<content_l.length;i++){
			char[] lower = content_l[i].toCharArray();
			for(int k=0;k<lower.length;k++){
				if(Character.isUpperCase(lower[k])){
					lower[k] = Character.toLowerCase(lower[k]);
				}
			}
			if(!stopword.contains(String.valueOf(lower))){
				String str="";
				Stemmer s = new Stemmer();
				s.add(lower,lower.length);
				s.stem();
				str = String.valueOf(s);
				buf.append(str);
				buf.append(" ");
			}
		}
		q.SetTopicId("a");
		q.SetQueryContent(buf.toString());

		return q;

	}


}
