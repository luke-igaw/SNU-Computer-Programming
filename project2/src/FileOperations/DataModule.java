package SNUSearch_김근우.src.FileOperations;

import java.io.*;

/*
    C: Data Module
    • 파일 접근이 필요한 모든 요청은 Data Module에서 담당
 */
public class DataModule {
    // 아이디 중복검사
    // condition: 아이디가 기존에 등록된 것과 중복되지 않아야 하며, 대소문자를 구분하지 않음
    public static boolean existId(String id){
        try{
            File file = new File("./data/userinfo.txt");

            if(!file.exists()) return false;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                String[] tmp = line.split(" ");
                if(tmp[0].equals(id)) return true;
            }
            return false;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    //유저 목록을 한줄씩 읽으며 아이디와 비밀번호가 매치되는 회원이 있는지 확인하기.
    public static boolean isMatchedPassword(String id, String pw) {
        try{
            File file = new File("./data/userinfo.txt");
            if(!file.exists()) return false;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                String[] tmp = line.split(" ");
                if(tmp[0].equals(id) && tmp[1].equals(pw)) return true;
            }
            return false;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    // 회원 탈퇴 목록에 존재하는 아이디인지 확인
    public static boolean existIdinLeavedUser(String id){
        try{
            File file = new File("./data/leavedinfo.txt");

            if(!file.exists()) return false;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                String[] tmp = line.split(" ");
                if(tmp[0].equals(id)) return true;
            }
            return false;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    // 회원 탈퇴 목록에서 한줄씩 읽으며 매치되는 이이디, 비밀번호 쌍이 있는지 검사
    public static boolean isMatchedPasswordinLeavedUser(String id, String pw) {
        try{
            File file = new File("./data/leavedinfo.txt");
            if(!file.exists()) return false;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                String[] tmp = line.split(" ");
                if(tmp[0].equals(id) && tmp[1].equals(pw)) return true;
            }
            return false;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
