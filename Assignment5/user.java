package EX4;

public class user {
    private String id; // user's id
    private String pw; // user's password

    //constructor
    public user(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public String getid(){ //getter function of id
        return id;
    }
    public String getpw(){ //getter function of pw
        return pw;
    }

    @Override
    public String toString(){
        return "id: " + id + " pw: " + pw;
    }
}
