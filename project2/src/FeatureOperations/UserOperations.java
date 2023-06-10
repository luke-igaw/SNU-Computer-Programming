package SNUSearch_김근우.src.FeatureOperations;

import java.io.FileNotFoundException;

/*
*************************************************************************
interface for Operations related to User.
*************************************************************************
 */

interface UserOperations {
    /*
    아이디와 비밀번호를 입력하고 드롭다운 리스트에서 “Join” 선택 후 버튼 클릭
    회원가입 성공 조건
    아이디가 기존에 등록된 것과 중복되지 않아야 하며, 대소문자를 구분하지 않음
    비밀번호는 4자 이상이어야 하며 알파뱃(영문자)로 시작해야만 하고 특수기호는 @, %만 허용 (# 허용 X)
     */
    boolean join(String id, String pw) throws FileNotFoundException;


    /*
    아이디와 비밀번호를 입력하고 드롭다운 리스트에서 “Login” 선택 후 버튼 클릭
    로그인 성공 조건: 아이디와 비밀번호가 기존 회원 가입했던 정보와 일치해야 함
    아이디와 비밀번호 모두 admin을 입력하고 로그인하면 관리자 모드 진입 (상세 내용은 9 page 참조)
    */
    boolean login(String id, String pw, boolean admin);

    /*
    아이디와 비밀번호를 입력하고 드롭다운 리스트에서 “Logout” 선택 후 버튼 클릭
    로그아웃 성공 조건: 로그인 성공 상태에서 로그인 한 아이디를 정확하게 입력함
    (단, 웹 페이지를 새로고침하면 자동 로그아웃이 됨, 패스워드는 비워 두거나 아무 값이 와도 OK, 같게 해도 OK)
     */
    boolean logout(String id);

    /*
    아이디와 비밀번호를 입력하고 드롭다운 리스트에서 “Leave“ 선택 후 버튼 클릭 회원탈퇴 성공 조건: 아이디와 비밀번호가 기존 회원 가입했던 정보와 일치해야 함
    */
    boolean leave(String id, String pw);

    /*
    아이디와 비밀번호를 입력하고 드롭다운 리스트에서 “Recover“ 선택 후 버튼 클릭 회원복구 성공 조건: 아이디와 비밀번호가 기존 회원 탈퇴했던 정보와 일치해야 함
     */
    boolean recover(String id, String pw);
}