import java.util.*;

public class ex1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력해 주세요:");
        String name = sc.nextLine();
        System.out.println("나이를 입력해 주세요.");
        int age = sc.nextInt();
        System.out.println(name + "님은 " + age + "살 이시군요!!");
    }
}
