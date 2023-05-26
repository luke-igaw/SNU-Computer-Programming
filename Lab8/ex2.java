import java.util.*;

public class ex2 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열을 입력해 주세요: ");
        String str = sc.next();
        System.out.print("찾을 문자열의 위치를 알려주세요: ");
        int n = sc.nextInt();
        System.out.println(str.charAt(n));
    }
}
