package ma.fstt;

public class SessionManager {

        private static Login loggedInUser;

        public static Login getLoggedInUser() {
            return loggedInUser;
        }

        public static void setLoggedInUser(Login user) {
            loggedInUser = user;
        }


}
