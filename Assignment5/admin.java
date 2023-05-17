package EX4;

import java.util.ArrayList;

public class admin extends user{
    private ArrayList<user> users = new ArrayList<user>(); // list of the users

    //constructor
    public admin(String id, String pw, ArrayList<user> users){ 
        super(id,pw);
        this.users = users;
    }

    //print users' information and the total number of users.
    public void print() { 
        System.out.println("현재 등록된 모든 회원들의 정보입니다.");
        for (user member : users) {
            if(member.getid() == "admin") continue;
            System.out.println(member.toString());
        }
        System.out.println("전체 회원 수: " + (users.size()-1));
    }
}
