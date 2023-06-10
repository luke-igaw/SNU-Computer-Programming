package SNUSearch_김근우.src.FeatureOperations;

import SNUSearch_김근우.src.FileOperations.DataModule;
import SNUSearch_김근우.src.FileOperations.IOModule;
import SNUSearch_김근우.src.SimpleHttpServer;
import java.io.FileNotFoundException;
import static SNUSearch_김근우.src.FeatureOperations.UtilityModule.isValidPassword;

/*
 Implementation of UserOperations Interface
 */
public class User implements UserOperations {
    private String id;
    private String pw;
    private boolean admin;
    public User(String id, String pw, boolean admin){
        this.id = id;
        this.pw = pw;
        this.admin = admin;
    }


    //회원가입 기능 GET /user/join
    @Override
    public boolean join(String id, String pw) throws FileNotFoundException {
        if(DataModule.existId(id)) return false; //1. it should check whether the id already exists.
        if(!isValidPassword(pw)) return false; //2. it should check the password conform the format.
        //3. if id and password is pass the condition,
        //   add id and password in userinfo file
        IOModule.updateUser(id,pw);
        return true;
    }

    // 로그인 기능 GET /user/login
    @Override
    public boolean login(String id, String pw, boolean admin){
        if(!DataModule.existId(id)) return false; //1. it should check whether the id already exists.
        if(!DataModule.isMatchedPassword(id,pw)) return false; //2. it should check the password matches to the id.
        return true;
    }

    @Override
    public boolean logout(String id){
        //1. it should check whether the id is same with the logged in ID.
        if(SimpleHttpServer.loggedInId.equals(id)) return true;
        else return false;
    }

    @Override
    public boolean leave(String id, String pw){
        if(!DataModule.existId(id)) return false; //1. it should check whether the id already exists.
        if(!DataModule.isMatchedPassword(id,pw)) return false; //2. it should check the password matches to the id.
        IOModule.updateLeavedUserList(id,pw); //회원탈퇴 유저 데이터 관리
        IOModule.removeLeavedUser(id,pw); //회원 목록에서 탈퇴 유저 삭제
        return true;
    }

    @Override
    public boolean recover(String id, String pw){
        if(!DataModule.existIdinLeavedUser(id)) return false; //1. 회원 탈퇴한 아이디가 맞는지 확인.
        if(!DataModule.isMatchedPasswordinLeavedUser(id,pw)) return false; //2. 비밀번호 맞는지 확인
        IOModule.updateUser(id,pw); // 유저 목록에 복구
        IOModule.recoverUser(id,pw); // 탈퇴 목록에서 삭제
        return true;
    }
}