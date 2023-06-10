package SNUSearch_김근우.src.FeatureOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UtilityModule {

    // check whether input password conform to the condition
    // condition: 비밀번호는 4자 이상이어야 하며 알파뱃(영문자)로 시작해야만 하고 특수기호는 @, %만 허용 (# 허용 X)
    // if the password conform to the conditon, return true. else return false
    public final static boolean isValidPassword(String pw) {
        String passwordPattern = "^[a-zA-Z][a-zA-Z0-9@%]{3,}$";
        return Pattern.compile(passwordPattern).matcher(pw).matches();
    }

    //for parsing ID, Password, User, q. etc. (parameters..)
    public static final Map<String, String> parseQueryParameters(String query) {
        Map<String, String> queryParameters = new HashMap<>();

        String[] params = query.split("&");

        for (String param : params) {
            String[] pair = param.split("=");

            if (pair.length > 1) {
                queryParameters.put(pair[0], pair[1]);
            } else {
                queryParameters.put(pair[0], "");
            }
        }
        return queryParameters;
    }

}