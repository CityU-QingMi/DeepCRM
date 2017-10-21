    boolean readIfThis(String tokenString) {

        if (tokenString.equals(token.tokenString)) {
            read();

            return true;
        }

        return false;
    }
