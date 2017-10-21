    void readNestedParenthesisedTokens() {

        readThis(Tokens.OPENBRACKET);

        do {
            read();

            if (token.tokenType == Tokens.OPENBRACKET) {
                readNestedParenthesisedTokens();
            }

            if (token.tokenType == Tokens.X_ENDPARSE) {
                throw unexpectedToken();
            }
        } while (token.tokenType != Tokens.CLOSEBRACKET);

        read();
    }
