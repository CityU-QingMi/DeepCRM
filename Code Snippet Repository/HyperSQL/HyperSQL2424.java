    private StatementCommand compileSetProperty() {

        // command is a no-op from 1.9
        read();

        String property;
        Object value;

        checkIsSimpleName();
        checkIsDelimitedIdentifier();

        property = token.tokenString;

        read();

        if (token.tokenType == Tokens.TRUE) {
            value = Boolean.TRUE;
        } else if (token.tokenType == Tokens.FALSE) {
            value = Boolean.FALSE;
        } else {
            checkIsValue();

            value = token.tokenValue;
        }

        read();

        Object[] args = new Object[] {
            property, value
        };

        return new StatementCommand(StatementTypes.SET_DATABASE_PROPERTY,
                                    args);
    }
