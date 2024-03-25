package uitls;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class PasswordEncoder {

    private PasswordEncoder() {
    }

    //Encode the password using sha256 algorithm and return it
    public static String encodePassword(String password){
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
