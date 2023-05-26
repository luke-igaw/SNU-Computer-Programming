import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class F {
    public static void main (String args[]) throws IOException{
        File dr = new File("db");
        if(!dr.exists()){
            dr.mkdir();
        }
        
        String[] str = {"aaa","bbb","ccc","ddd","eee"};
        BufferedWriter bw = new BufferedWriter(new FileWriter("db/actors.txt"));

        String line;
        int i = 0;
        while(i < 5){
            bw.write(str[i] + "\n");
            i++;
        }
        bw.flush();
        bw.close();
    }
}
