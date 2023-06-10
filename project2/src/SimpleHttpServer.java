package SNUSearch_김근우.src;

import SNUSearch_김근우.src.FeatureOperations.User;
import static SNUSearch_김근우.src.FeatureOperations.UtilityModule.parseQueryParameters;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import SNUSearch_김근우.src.FileOperations.IOModule;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class SimpleHttpServer {
    public static String loggedInId; //who is logged in now.

    //main method is not used because MainRunner execute runServer() method.
    public static void main(String[] args) {
        int port = 8080; // If the 8080 port isn't available, try to use another port number.
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Create a new thread to handle the client request
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for executing SimpleHttpServer and SimpleHttpClient at the same time
    public static void runServer() {
        int port = 8080; // If the 8080 port isn't available, try to use another port number.
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Create a new thread to handle the client request
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                User user = null;
                String request = in.readLine();
                System.out.println("Received request: " + request);

                // Extract the path from the request
                String[] requestParts = request.split(" ");
                String path = requestParts[1];
                System.out.println(path);

                // Read the HTML file and send it as the response
                if (path.equals("/")) {  // CASE 1: It sends index.html page to the client.
                    String filePath = "src/index.html";  // Default file to serve
                    File file = new File(filePath);  // Replace with the actual path to your HTML files
                    if (file.exists() && file.isFile()) {
                        System.out.println(file.getAbsolutePath());
                        String contentType = Files.probeContentType(Paths.get(filePath));
                        String content = new String(Files.readAllBytes(Paths.get(filePath)));

                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Type: " + contentType);
                        out.println("Content-Length: " + content.length());
                        out.println();
                        out.println(content);
                    } else {
                        // File not found
                        out.println("HTTP/1.1 404 Not Found");
                        out.println();
                    }
                }

                // CASE 2: get other requests and send it as the response.
                else {
                    //For parsing cmd from requests.
                    String[] pathparse = path.split("/");
                    String[] pathparse2 = pathparse[2].split("\\?");
                    String cmd = pathparse2[0];

                    //USER 관련 기능 시작
                    //회원 가입 기능
                    if (cmd.equals("join")) {
                        //parsing parameters.
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String id = params.get("id");
                        String pw = params.get("passwd");
                        user = new User(id, pw, false);

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        //회원가입 성공 케이스 200 response
                        if (user.join(id, pw)) {
                            String content = "You have successfully registered as a member of our website.";
                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: text/plain");
                            out.println("Content-Length: " + content.length());
                            out.println();
                            out.println(content);
                        }
                    }

                    //로그인 기능
                    else if (cmd.equals("login")) {
                        //parsing parameters.
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String id = params.get("id");
                        String pw = params.get("passwd");
                        user = new User(id, pw, false);

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        //로그인 성공 케이스 200 response
                        if (user.login(id, pw, false)) {
                            loggedInId = id;
                            String content = "You have successfully logged in";
                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: text/plain");  // application/json would be OK!
                            out.println("Content-Length: " + content.length());
                            out.println();
                            out.println(content);
                        }
                    }

                    //로그아웃 기능
                    else if (cmd.equals("logout")) {
                        //parsing parameters.
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String id = params.get("id");
                        user = new User(id, "", false);

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        //로그아웃 성공 케이스 200 response
                        if (user.logout(id)) {
                            loggedInId = null;
                            String content = "You have successfully logged out";
                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: text/plain");  // application/json would be OK!
                            out.println("Content-Length: " + content.length());
                            out.println();
                            out.println(content);
                        }
                    }

                    //회원탈퇴 기능
                    else if (cmd.equals("leave")) {
                        //parsing parameters.
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String id = params.get("id");
                        String pw = params.get("passwd");
                        user = new User(id, pw, false);

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        //회원탈퇴 성공 케이스 200 response
                        if (user.leave(id, pw)) {
                            String content = "Your id was successfully removed.";
                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: text/plain");  // application/json would be OK!
                            out.println("Content-Length: " + content.length());
                            out.println();
                            out.println(content);
                        }
                    }

                    //탈퇴 회원 복구 기능
                    else if (cmd.equals("recover")) {
                        //parsing parameters.
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String id = params.get("id");
                        String pw = params.get("passwd");
                        user = new User(id, pw, false);

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        //회원탈퇴 성공 케이스 200 response
                        if (user.recover(id, pw)) {
                            String content = "Your id was successfully recovered.";
                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: text/plain");  // application/json would be OK!
                            out.println("Content-Length: " + content.length());
                            out.println();
                            out.println(content);
                        }
                    }
                    //USER 관련 기능 끝

                    //DATA 관련 기능 시작

                    //검색 기능
                    else if (cmd.equals("search")) {
                        //parsing parameters.
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String searchQuery = params.get("q");  //the search query parameter is "q"
                        String id = params.get("user");
                        String encodedSearchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyCz0_bpS0b1x9UKDVTrTI4Apq90HaZMy1M&cx=e7ce36b20746140fb&q="
                                + encodedSearchQuery;

                        // Create a URL object from the specified URL
                        URL requestUrl = new URL(url);
                        // Open a connection to the URL
                        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                        // Set the request method (GET by default)
                        connection.setRequestMethod("GET");
                        // Send the request and receive the response
                        int responseCode = connection.getResponseCode();

                        // Read the response
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        // Print the response using external library Gson. (검색 성공 케이스 _ 200 response)
                        if (responseCode == 200) {
                            Gson gson = new Gson();
                            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

                            // 이때,HTTP 응답 메시지에 꼭 포함시켜야 하는 데이터는 아래와 같음
                            JsonObject queries = jsonResponse.getAsJsonObject("queries");
                            JsonArray requestArray = queries.getAsJsonArray("request");
                            JsonObject requestObject = requestArray.get(0).getAsJsonObject();
                            //queries.request[0].searchTerms
                            String searchTerms = requestObject.get("searchTerms").getAsString();
                            //queries.request[0].totalResults
                            int totalResults = requestObject.get("totalResults").getAsInt();

                            //items[0].title, items[0].link (item[1], item[2]의 동일 정보 포함)
                            JsonArray items = jsonResponse.getAsJsonArray("items");
                            ArrayList<String> titles = new ArrayList<>();
                            ArrayList<String> links = new ArrayList<>();

                            //검색 결과는 보통 10개가 전달되는데, 그 중 TOP3 (index 0~2)만 추려서 index.html에 응답으로 전송
                            for (int i = 0; i < 3; i++) {
                                JsonObject item = items.get(i).getAsJsonObject();
                                titles.add(item.get("title").getAsString());
                                links.add(item.get("link").getAsString());
                            }

                            JsonObject finalResponse = new JsonObject();
                            finalResponse.addProperty("searchTerms", searchTerms);
                            finalResponse.addProperty("totalResults", totalResults);
                            JsonArray titlesArray = gson.toJsonTree(titles).getAsJsonArray();
                            finalResponse.add("titles", titlesArray);
                            JsonArray linksArray = gson.toJsonTree(links).getAsJsonArray();
                            finalResponse.add("links", linksArray);

                            String finalResponseString = gson.toJson(finalResponse);

                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: application/json");
                            out.println("Content-Length: " + finalResponseString.length());
                            out.println();
                            out.println(finalResponseString);
                        } else { //검색 실패 케이스
                            out.println("HTTP/1.1 " + responseCode + " Error");
                            out.println();
                        }
                        connection.disconnect();
                    }

                    // MY 검색어 저장 기능 -- 실제 검색할 필요 없이 검색어만 뽑아서 data에 저장
                    else if (cmd.equals("save_data")) {
                        //parsing parameters
                        Map<String, String> params = parseQueryParameters(pathparse2[1]);
                        String searchQuery = params.get("q");  // the search query parameter is "q"
                        String id = params.get("user");
                        String encodedSearchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());

                        IOModule.saveRequest(id, path); //save Request for load_log feature.

                        IOModule.saveKeywordForUser(loggedInId, encodedSearchQuery); //형식에 맞게 검색어 저장
                        IOModule.saveKeyword(encodedSearchQuery, new File("./data/searchkeyword.txt")); //save keyword for load_hot feature

                        String content = "Search Keyword was successfully saved";
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Type: text/plain");  // application/json would be OK!
                        out.println("Content-Length: " + content.length());
                        out.println();
                        out.println(content);
                    }

                    // MY 검색어 로드 기능 -- 모든 검색어
                    else if (cmd.equals("load_data")) {
                        try {
                            //parsing parameters
                            Map<String, String> params = parseQueryParameters(pathparse2[1]);
                            String id = params.get("user");

                            IOModule.saveRequest(id, path); //save Request for load_log feature.

                            //File that has user's search keyword history
                            File file = new File("./data/" + loggedInId + "_keyword.txt");
                            if (file.exists()) {
                                BufferedReader br = new BufferedReader(new FileReader(file));
                                String line;
                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: text/plain");  // application/json would be OK!
                                out.println("Content-Length: " + file.length());
                                out.println();
                                while((line = br.readLine()) != null){
                                    out.println(line);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    // 친구 검색어 로드 기능
                    else if (cmd.equals("load_fri")) {
                        try {
                            //parsing parameters
                            Map<String, String> params = parseQueryParameters(pathparse2[1]);
                            String friendQuery = params.get("q");
                            String id = params.get("user");

                            IOModule.saveRequest(id, path); //save Request for load_log feature.

                            //File that has friend's search keyword history
                            File file = new File("./data/" + friendQuery + "_keyword.txt");
                            if (file.exists()) {
                                BufferedReader br = new BufferedReader(new FileReader(file));
                                String line;
                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: text/plain");  // application/json would be OK!
                                out.println("Content-Length: " + file.length());
                                out.println();
                                while((line = br.readLine()) != null){
                                    out.println(line);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    //HOT 검색어 로드 기능
                    //Search를 통해 실 검색을 한 키워드가 아닌, save_data를 통해 저장된 데이터 중 HOT 키워드 추출
                    else if (cmd.equals("load_hot")) {
                        try {
                            //parsing parameters
                            Map<String, String> params = parseQueryParameters(pathparse2[1]);
                            String id = params.get("user");

                            IOModule.saveRequest(id, path); //save Request for load_log feature.

                            Map<String, Integer> wordCount = new HashMap<>(); //빈도수 계산을 위한 HashMap

                            BufferedReader reader = new BufferedReader(new FileReader("./data/searchkeyword.txt"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String word = line.trim().toLowerCase(); // Convert to lowercase for case insensitive counting
                                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                            }

                            // Sort the entries by value in descending order and then by key in ascending order
                            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCount.entrySet());
                            entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
                                @Override
                                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                                    int cmp = -e1.getValue().compareTo(e2.getValue());
                                    if (cmp != 0) return cmp;
                                    else return e1.getKey().compareTo(e2.getKey());
                                }
                            });

                            StringBuilder sb = new StringBuilder();
                            for (Map.Entry<String, Integer> entry : entryList) {
                                sb.append(entry.getKey()).append("\n");
                            }
                            String content = sb.toString();

                            out.println("HTTP/1.1 200 OK");
                            out.println("Content-Type: text/plain");
                            out.println("Content-Length: " + content.getBytes().length);
                            out.println();
                            out.println(content);
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    //관리자 기능 시작
                    //회원정보 로드 기능
                    else if (cmd.equals("load_acc")) {
                        if (loggedInId.equals("admin")) { //아이디가 admin인 경우에만 동작
                            try{
                                //parsing parameters
                                Map<String, String> params = parseQueryParameters(pathparse2[1]);
                                String id = params.get("user");

                                IOModule.saveRequest(id, path); //save Request for load_log feature.

                                BufferedReader br = new BufferedReader(new FileReader("./data/userinfo.txt"));
                                String line;
                                StringBuilder sb = new StringBuilder();
                                while((line = br.readLine()) != null){
                                    String[] tmp = line.split(" "); //"아이디 비밀번호" 형식에서 아이디만 추출
                                    sb.append(tmp[0]).append("\n");
                                }
                                String content = sb.toString();
                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: text/plain");
                                out.println("Content-Length: " + content.getBytes().length);
                                out.println(); // blank line separates headers and content
                                out.println(content);
                                br.close();
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }

                    //시스템 로그정보 로드 기능
                    else if (cmd.equals("load_log")) {
                        if (loggedInId.equals("admin")) { //아이디가 admin일 때만 동작
                            try{
                                //parsing parameters.
                                Map<String, String> params = parseQueryParameters(pathparse2[1]);
                                String id = params.get("user");

                                IOModule.saveRequest(id, path); //save Request for load_log feature.

                                BufferedReader br = new BufferedReader(new FileReader("./data/requests.txt"));
                                String line;
                                StringBuilder sb = new StringBuilder();

                                //지금까지 있었던 requests를 한줄씩 스트링 빌더에 append
                                while((line = br.readLine()) != null){
                                    sb.append(line + "\n");
                                }
                                String content = sb.toString();
                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: text/plain");
                                out.println("Content-Length: " + content.getBytes().length);
                                out.println(); // blank line separates headers and content
                                out.println(content);
                                br.close();
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //관리자 기능 끝
            //DATA 관련 기능 끝
        }
    }
}