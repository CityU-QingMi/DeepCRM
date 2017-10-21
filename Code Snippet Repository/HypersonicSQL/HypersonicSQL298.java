    void readUnquotedIdentifier(String ident) {

        checkIsSimpleName();

        if (!token.tokenString.equals(ident)) {
            throw unexpectedToken();
        }

        read();
    }
