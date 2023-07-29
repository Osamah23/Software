package sakancomMain;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class SignUpService {
    private Map<String, UserData> users; // In-memory storage for user data (username, user data)

    public SignUpService() {
        users = new HashMap<>();
    }

    public boolean signUp(String username, String password, UserType userType) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false; // Invalid input, sign-up failed
        }

        if (users.containsKey(username)) {
            return false; // Username already taken, sign-up failed
        }

        users.put(username, new UserData(username, password, userType));
        return true; // Sign-up successful
    }

    // UserData class to store additional user information like userType
    private static class UserData {
        private String username;
        private String password;
        private UserType userType;

        public UserData(String username, String password, UserType userType) {
            this.username = username;
            this.password = password;
            this.userType = userType;
        }

        // Getters and setters (optional depending on requirements)
    }
}

    


