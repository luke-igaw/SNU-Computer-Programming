package EX4;

import java.util.ArrayList;
import java.util.Scanner;

public class login {
    public user loginuser; //loginned user 
    private ArrayList<user> users; // users' list
    public admin adminuser;
    //constructor
    public login(){
        users = new ArrayList<user>();
        adminuser = new admin("admin", "admin", users);
        users.add(adminuser);
        loginuser = null;
    }

    /*
     * sign up
     * It should contains whether the id and pw satisfy the given requirements
     */
    public void signup(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" ** 주의 ** 아이디와 비밀번호는 알파벳 대소문자와 숫자로만 이뤄져야 합니다.");
        System.out.println(" ** 주의 ** 아이디는 대소문자를 구분하지 않습니다.");
        System.out.println(" ** 주의 ** 비밀번호는 10자 이상이여야 합니다.");

        String id;
        String pw;
        //get id from user
        while(true){
            System.out.print("아이디를 입력하세요: ");
            id = sc.nextLine().toLowerCase(); //case insensitive
    
            //to check whether the id already exists.
            if(checkexist(id)){
                System.out.println("이미 존재하는 아이디입니다.\n다시 입력해주세요.");
                continue;
            }

            if(!checkIdRequirement(id)){
                System.out.println("특수문자는 포함할 수 없습니다.");
                System.out.println("알파벳 대소문자와 숫자로만 입력해주세요.");
                continue;
            }
            break;
        }

        //get password from user.
        while(true){
            System.out.print("비밀번호를 입력하세요: ");
            pw = sc.nextLine();

            if(!checkPwRequirement(pw)){
                continue;
            }
            break;
        }

        //add user to users' list
        users.add(new user(id, pw));

        System.out.println(id + "님 회원 가입을 축하합니다.");
        
    }

    //to check whether the id is already in the list
    public boolean checkexist(String id){
        for(user member : users){
            if(member.getid().equals(id)) return true;
        }
        return false;
    }

    //to check whether it contains Exclamation marks using Regular Expression.
    public boolean noExclamation(String str){ 
        return str.matches("[a-zA-Z0-9]+");
    }

    //to check whether it satisfies the requirement for id
    public boolean checkIdRequirement(String id){
        return noExclamation(id);
    }

    //to check whether it satisfies the requirement for pw
    //10자 이상, 특수문자x
    public boolean checkPwRequirement(String pw){
        boolean tmp = true;
        if(pw.length() < 10){
            System.out.println("길이가 너무 짧습니다.");
            tmp = false;
        }
        if(!noExclamation(pw)){
            System.out.println("특수문자는 포함할 수 없습니다.");
            System.out.println("알파벳 대소문자와 숫자로만 입력해주세요.");
            tmp = false;
        }
        return tmp;
    }

    //sign in
    public void signin(){
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String pw = sc.nextLine();
        if(!checkexist(id)){
            System.out.println("존재하지 않는 회원 아이디입니다.");
            System.out.println("회원가입을 진행해 주시길 바랍니다.");
            
            return;
        }
        for (user member : users) {
            if (member.getid().equals(id) && member.getpw().equals(pw)) {
                loginuser = member;
                System.out.println(loginuser.getid() + " 계정으로 로그인 되었습니다.");
                
                return;
            }
        }
        System.out.println("비멀번호가 일치하지 않습니다.");
        
    }

    //logout
    public void logout(){
        loginuser = null;
        System.out.println("로그아웃 되었습니다.");
    }

    //withdrawal
    public void withdraw(){
        for(user member : users){
            if(member.getid().equals(loginuser.getid())){
                users.remove(loginuser);
                System.out.println("탈퇴가 완료되었습니다.");
                System.out.println("이용해 주셔서 감사합니다.");
            }
        }
    }
}
