    Boolean readIfNotExists() {

        Boolean ifNot = Boolean.FALSE;

        if (token.tokenType == Tokens.IF) {
            int position = getPosition();

            read();

            if (token.tokenType == Tokens.NOT) {
                read();
                readThis(Tokens.EXISTS);

                ifNot = Boolean.TRUE;
            } else {
                rewind(position);

                ifNot = Boolean.FALSE;
            }
        }

        return ifNot;
    }
