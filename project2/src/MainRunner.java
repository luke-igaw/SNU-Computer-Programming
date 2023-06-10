package SNUSearch_김근우.src;

public class MainRunner {

    public static void main(String[] args) {
        // Run SimpleHttpServer
        new Thread(() -> SimpleHttpServer.runServer()).start();
    }
}

//5. readme.md 작성
//          프로젝트 최상단 디렉토리 내 README.md 텍스트 파일을 생성하고 10-20 lines 분량으로 아래 내용 간략히 기재
//          1.요구사항14개중미구현항목명시및이유, 2.가장어려웠던기능및접근방법, 3.느낀점(유익한점,애로사항,개선점등) 4. 사용 외부 라이브러리