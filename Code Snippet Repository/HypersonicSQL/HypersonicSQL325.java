    String readPassword() {

        String tokenS = token.tokenString;

        if (isUndelimitedSimpleName() || isDelimitedSimpleName()) {
            read();
        } else {
            readQuotedString();
        }

        return tokenS;
    }
