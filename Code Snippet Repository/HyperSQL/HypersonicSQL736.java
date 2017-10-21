    public void scanBitStringWithQuote() {

        resetState();
        scanSeparator();

        if (charAt(currentPosition) != '\'') {
            token.tokenType   = Tokens.X_MALFORMED_BIT_STRING;
            token.isMalformed = true;

            return;
        }

        scanBitString();
    }
