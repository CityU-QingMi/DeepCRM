    boolean readIfThis(int tokenId) {

        if (token.tokenType == tokenId) {
            read();

            return true;
        }

        return false;
    }
