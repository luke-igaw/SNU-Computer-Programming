package SNUSearch_김근우.src.FileOperations;
import java.io.*;

/*
 * D: File I/O Module
 * • 파일을 생성하고 접근하는 부분을 전담하는 모듈 (C와 통합하여 개발하는 방식도 OK)
 * • 특정 파일이 없는 경우 바로 신규 생성해야 하며,있는 경우 그 안에 값을 업데이트하는 방식으로 동작
 */
public class IOModule {
    // Make userinfo file or update it that contains users' ID, PASSWORD information.
    // 유저 목록에 유저 추가
    public static void updateUser(String id, String pw){
        try{
            File file = new File("./data/userinfo.txt");
            File directory = new File("./data");
            if (!directory.exists()) directory.mkdirs(); // 디렉토리 없으면 디렉토리 생성
            if(!file.exists()) file.createNewFile(); // 파일 없으면 파일 생성
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id.toLowerCase() + " " + pw + "\n"); //write on file & id is letter insensitive
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // 회원 탈퇴 목록에 유저 추가
    public static void updateLeavedUserList(String id, String pw){
        try{
            File file = new File("./data/leavedinfo.txt");
            File directory = new File("./data");
            if (!directory.exists()) directory.mkdirs(); // 디렉토리 없으면 디렉토리 생성
            if(!file.exists()) file.createNewFile(); // 파일 없으면 파일 생성
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id.toLowerCase() + " " + pw + "\n");
            bw.close(); // add this line to close the BufferedWriter
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // 회원 목록에서 탈퇴 유저 삭제
    // 임시파일에 옮기면서 유저 삭제 -> 임시 파일을 다시 원래 파일로 옮기기 -> 임시 파일 삭제
    public static void removeLeavedUser(String id, String pw){
        try{
            String target = id + " " + pw;
            File file = new File("./data/userinfo.txt");
            File tempfile = new File("./data/tempinfo.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile));
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals(target)) continue;
                else writer.write(line + "\n");
            }
            writer.close();
            reader.close();

            // Check whether the original file was successfully deleted, if not, throws an exception
            if (!file.delete()) {
                throw new Exception("Could not delete file");
            }

            // Check whether the temporary file was successfully renamed to the original file, if not, throws an exception
            if (!tempfile.renameTo(file)) {
                throw new Exception("Could not rename file");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 탈퇴 목록에 있는 유저 삭제 (recover했을 때)
    // removeLeavedUser(...)와 동일하게 임시 파일 사용.
    public static void recoverUser(String id, String pw){
        try{
            String target = id + " " + pw;
            File file = new File("./data/leavedinfo.txt");
            File tempfile = new File("./data/tempinfo.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile));
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals(target)) continue;
                else writer.write(line + "\n");
            }
            writer.close();
            reader.close();

            // Check whether the original file was successfully deleted, if not, throws an exception
            if (!file.delete()) {
                throw new Exception("Could not delete file");
            }

            // Check whether the temporary file was successfully renamed to the original file, if not, throws an exception
            if (!tempfile.renameTo(file)) {
                throw new Exception("Could not rename file");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //save search keyword.
    public static void saveKeyword(String keyword, File file){
        try{
            File directory = new File("./data");
            if (!directory.exists()) directory.mkdirs(); // 디렉토리 없으면 디렉토리 생성
            if(!file.exists()) file.createNewFile(); // 파일 없으면 파일 생성
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(keyword + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //save keyword for individual user.
    public static void saveKeywordForUser(String id, String keyword){
        try{
            File file = new File("./data/" + id + "_keyword.txt");
            File directory = new File("./data");
            if (!directory.exists()) directory.mkdirs(); // 디렉토리 없으면 디렉토리 생성
            if(!file.exists()) file.createNewFile(); // 파일 없으면 파일 생성
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(keyword + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //save request for load_log feature.
    public static void saveRequest(String id, String request){
        try{
            File file = new File("./data/requests.txt");
            File directory = new File("./data");
            if (!directory.exists()) directory.mkdirs(); // 디렉토리 없으면 디렉토리 생성
            if(!file.exists()) file.createNewFile(); // 파일 없으면 파일 생성
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + id + "]" + request + "\n");
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}



