    void readThis(int tokenId) {

        if (token.tokenType != tokenId) {
            String required = Tokens.getKeyword(tokenId);

            throw unexpectedTokenRequire(required);
        }

        read();
    }
