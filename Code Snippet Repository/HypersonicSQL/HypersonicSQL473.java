    void readRoutineJavaBody(Routine routine) {

        if (routine.getLanguage() != Routine.LANGUAGE_JAVA) {
            throw unexpectedToken();
        }

        read();
        readThis(Tokens.NAME);
        checkIsQuotedString();
        routine.setMethodURL((String) token.tokenValue);
        read();

        if (token.tokenType == Tokens.PARAMETER) {
            read();
            readThis(Tokens.STYLE);
            readThis(Tokens.JAVA);
        }
    }
