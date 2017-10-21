    String parseSQLStateValue() {

        readIfThis(Tokens.VALUE);
        checkIsQuotedString();

        String sqlState = token.tokenString;

        if (sqlState.length() != 5) {
            throw Error.parseError(ErrorCode.X_42607, null,
                                   scanner.getLineNumber());
        }

        read();

        return sqlState;
    }
