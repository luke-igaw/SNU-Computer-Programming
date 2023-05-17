import EX4.login;
import EX4.user;
import EX4.admin;
import java.util.Scanner;
import java.io.IOException;

/*
 * [프로그램 실행 방법] -- 더 효율적인 커맨드가 있을 거 같은데 잘 모르겠습니다. 저는 아래와 같이 실행했습니다.
 * 1. 모든 파일을 같은 폴더에서
 * 2. javac Main.java login.java user.java admin.java (in terminal)
 * 3. java -d . login.java user.java admin.java
 * 4. java Main
 * ************************************************************************************
 * [참고]
 * 1. 5/17 작성
 * 2. 가독성이 떨어질 수도 있지만, 출력간의 공백은 넣지 않았습니다. (pdf 내 Problem -  B - ii를 보고 공백이 없다고 생각하고 최초 구현)
 * 3. 최초 구현 약 2시간, 동영상 보며 마이너한 버그 잡아내는데 1시간으로 총 3시간 걸림.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        login loginprogram = new login();
        Scanner sc = new Scanner(System.in);
        //initiate program
        while(true){
            System.out.println("어서오세요. 간단한 로그인 프로그램입니다.");
            System.out.println("원하시는 업무를 선택해 주세요.");
            System.out.print("1. 로그인 2. 회원가입 3. 종료\n선택하기: ");
            int cmd = sc.nextInt(); //command in
            if(cmd == 1){ //signin
                loginprogram.signin();
                if(loginprogram.loginuser == null) continue;
                int cmd1;
                if(loginprogram.loginuser.getid() == "admin"){
                    while(true){
                        System.out.println("원하시는 업무를 선택해 주세요.");
                        System.out.print("1. 전체 회원 조회 2. 로그아웃\n선택하기: ");
                        cmd1 = sc.nextInt();
                        if(cmd1 == 1) {
                            loginprogram.adminuser.print();
                            continue;
                        }
                        else if(cmd1 == 2) {
                            loginprogram.logout();
                            break;
                        }
                        else{
                            System.out.println("잘못 입력하였습니다. 1 ~ 2 숫자를 입력해 주세요.");
                        }
                    }
                }
                else{
                    System.out.println("원하시는 업무를 선택해 주세요.");
                    System.out.print("1. 탈퇴하기 2. 로그아웃\n선택하기: ");
                    cmd1 = sc.nextInt();
                    if(cmd1 == 1) {
                        loginprogram.withdraw();
                    }
                    else if(cmd1 == 2) {
                        loginprogram.logout();
                    }
                }
            }
            else if(cmd == 2){ //signup
                loginprogram.signup();
                System.out.println("원하시는 업무를 선택해 주세요.");
            }
            else if(cmd == 3){ //terminate the program.
                System.out.println("*********************************");
                System.out.println("로그인 프로그램을 종료합니다.");
                System.out.println("*********************************");
                break;
            }
            else{ //to catch wrong input
                System.out.println("잘못된 입력입니다! 프로그램을 다시 시작합니다.");
                continue;
            }
        }
    }
}