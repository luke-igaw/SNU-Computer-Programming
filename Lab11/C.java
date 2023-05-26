import java.util.Arrays;

public class C {
    public static void main(String args[]){
        String ip_addr = "IP address ?147.46.100.100";
        String[] result = ip_addr.split("\s\\?|\\.");
        System.out.println(Arrays.toString(result));
    }
}
