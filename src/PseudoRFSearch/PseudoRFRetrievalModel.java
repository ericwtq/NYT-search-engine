package PseudoRFSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import Classes.Document;
import Classes.Query;
import IndexingLucene.MyIndexReader;
import SearchLucene.QueryRetrievalModel;
import com.sun.javafx.fxml.expression.Expression;
import java.util.Collections;
import java.util.Comparator;

public class PseudoRFRetrievalModel {

	MyIndexReader ixreader;
	
	public PseudoRFRetrievalModel(MyIndexReader ixreader)
	{
		this.ixreader=ixreader;
	}
	
	/**
	 * Search for the topic with pseudo relevance feedback in 2017 spring assignment 4. 
	 * The returned results (retrieved documents) should be ranked by the score (from the most relevant to the least).
	 * 
	 * @param aQuery The query to be searched for.
	 * @param TopN The maximum number of returned document
	 * @param TopK The count of feedback documents
	 * @param alpha parameter of relevance feedback model
	 * @return TopN most relevant document, in List structure
	 */
	public List<Document> RetrieveQuery( Query aQuery, int TopN, int TopK, double alpha) throws Exception {	
		// this method will return the retrieval result of the given Query, and this result is enhanced with pseudo relevance feedback
		// (1) you should first use the original retrieval model to get TopK documents, which will be regarded as feedback documents
		// (2) implement GetTokenRFScore to get each query token's P(token|feedback model) in feedback documents
		// (3) implement the relevance feedback model for each token: combine the each query token's original retrieval score P(token|document) with its score in feedback documents P(token|feedback model)
		// (4) for each document, use the query likelihood language model to get the whole query's new score, P(Q|document)=P(token_1|document')*P(token_2|document')*...*P(token_n|document')

		int ref = 0;
		for(int i=0;i<ixreader.getIndexTotalCount();i++){
			ref = ref + ixreader.docLength(i);
		}

		//get P(token|feedback documents)
		HashMap<String,Double> TokenRFScore=GetTokenRFScore(aQuery,TopK);

		//calculate the score
		String []token = aQuery.GetQueryContent().split(" ");
		double []cf_q = new double[token.length];
		int [][]postinglist;
		HashMap<Integer,HashMap<String,Integer>> temp = new HashMap<>();
		for(int i=0;i<token.length;i++){
			cf_q[i] = ixreader.CollectionFreq(token[i]);
			postinglist = ixreader.getPostingList(token[i]);
			if(postinglist != null){
				for(int j=0;j<postinglist.length;j++){
					if(temp.containsKey(postinglist[j][0])){
						(temp.get(postinglist[j][0])).put(token[i],postinglist[j][1]);
					}
					else{
						HashMap<String,Integer> temp2 = new HashMap<>();
						temp2.put(token[i],postinglist[j][1]);
						temp.put(postinglist[j][0],temp2);
					}
				}
			}
		}

		double x = 2000;
		List<Document> document = new ArrayList<>();

		for(int k=0;k<ixreader.getIndexTotalCount();k++){
			double score = 1;
			int dl = ixreader.docLength(k);
			for(int m=0;m<token.length;m++){
				double c_f = cf_q[m];
				int c_w_d = 0;
				if(temp.containsKey(k) && (temp.get(k)).containsKey(token[m])){
					c_w_d = temp.get(k).get(token[m]);
				}
				if(c_f != 0) {
					score = score * (alpha * ((c_w_d + x * (c_f / ref)) / (dl + x)) + (1 - alpha) * TokenRFScore.get(token[m]));
				}
				else {
					score = score * (alpha * ((c_w_d + x * (1 /1 + ref)) / (dl + x)) + (1 - alpha) * TokenRFScore.get(token[m]));
				}
			}
			Document d = new Document(String.valueOf(k),ixreader.getDocno(k),score);
			document.add(d);
		}


		// sort all retrieved documents from most relevant to least, and return TopN

		//override the method to sort the list by the score in documents
		Collections.sort(document, new Comparator<Document>(){
			public int compare(Document o1, Document o2) {
				Document d1=(Document)o1;
				Document d2=(Document)o2;
				if(d1.score()>d2.score()){
					return -1;
				}else if(d1.score()==d2.score()){
					return 0;
				}else{
					return 1;
				}
			}
		});

		List<Document> results = new ArrayList<>();
		for(int n=0;n<TopN;n++){
			results.add(document.get(n));
		}

		return results;
	}
	
	public HashMap<String,Double> GetTokenRFScore(Query aQuery,  int TopK) throws Exception
	{
		// for each token in the query, you should calculate token's score in feedback documents: P(token|feedback documents)
		// use Dirichlet smoothing
		// save <token, score> in HashMap TokenRFScore, and return it
		QueryRetrievalModel qr = new QueryRetrievalModel(ixreader);
		List<Document> feedback_documents = qr.retrieveQuery(aQuery,TopK);
		int ref = 0;



		HashMap<String,Double> TokenRFScore=new HashMap<String,Double>();
		HashSet<Integer> fd_docid = new HashSet<>();
		for(Document a:feedback_documents){
			fd_docid.add(Integer.valueOf(a.docid()));
			ref = ref + ixreader.docLength(Integer.valueOf(a.docid()));
		}
		String []token = (aQuery.GetQueryContent()).split(" ");
		double []cf_q = new double[token.length];
		int [][]postinglist;
		int c;
		for(int i=0;i<token.length;i++){
			cf_q[i] = 0;
			postinglist = ixreader.getPostingList(token[i]);
			if(postinglist != null){
				c = 0;
				for(int j=0;j<postinglist.length;j++){
					if(fd_docid.contains(postinglist[j][0])){
						c = c + postinglist[j][1];
					}
				}
				cf_q[i] = c;
			}

			TokenRFScore.put(token[i],cf_q[i]/ref);
		}

		return TokenRFScore;
	}
	
	
}