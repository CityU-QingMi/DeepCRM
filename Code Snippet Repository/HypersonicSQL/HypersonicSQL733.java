    public void scanUUIDStringWithQuote() {

        try {
            hyphenInBinary = true;

            scanBinaryStringWithQuote();

            if (token.tokenValue instanceof BinaryData) {
                if (((BinaryData) token.tokenValue).length(null) != 16) {
                    token.tokenType   = Tokens.X_MALFORMED_BINARY_STRING;
                    token.isMalformed = true;
                }
            }
        } finally {
            hyphenInBinary = false;
        }
    }
