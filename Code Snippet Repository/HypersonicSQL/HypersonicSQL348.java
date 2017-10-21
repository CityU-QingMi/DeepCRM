    boolean processTrueOrFalse() {

        if (token.namePrefix != null) {
            throw unexpectedToken();
        }

        if (token.tokenType == Tokens.TRUE) {
            read();

            return true;
        } else if (token.tokenType == Tokens.FALSE) {
            read();

            return false;
        } else {
            throw unexpectedToken();
        }
    }
