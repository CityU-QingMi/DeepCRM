    public void scanBinaryStringWithQuote() {

        resetState();
        scanSeparator();

        if (charAt(currentPosition) != '\'') {
            token.tokenType   = Tokens.X_MALFORMED_BINARY_STRING;
            token.isMalformed = true;

            return;
        }

        scanBinaryString();
    }
