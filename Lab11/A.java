import java.io.*;
import java.util.*;

public class A {
    public static void main(String args[]){
        String str = new String("tomato");
        String left = str.substring(0,2);
        String right = str.substring(4);
        System.out.println(left + " " + right);
    }
}
