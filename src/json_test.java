import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;


public class json_test {

    public static void main(String[] args) throws Exception{

        HashMap<String,HashMap<String,String>> j = new HashMap<>();

        FileReader fr = new FileReader("//Users//ericwtq//PycharmProjects//predata//ir_topstory//json_re.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            String url = line;
            line = br.readLine();
            String title = line;
            line = br.readLine();
            String author = line;
            line = br.readLine();
            String date = line;
            line = br.readLine();
            String content = line;
            HashMap<String,String> temp = new HashMap<>();
            temp.put("title",title);
            temp.put("author",author);
            temp.put("date",date);
            temp.put("content",content);
            j.put(url,temp);
        }

        br.close();
        fr.close();
        System.out.println(j.size());

    }

}
