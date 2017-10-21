    void readThis(String tokenString) {

        if (!tokenString.equals(token.tokenString)) {
            String required = tokenString;

            throw unexpectedTokenRequire(required);
        }

        read();
    }
