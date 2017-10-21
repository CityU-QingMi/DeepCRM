    public void scanNext() {

        if (currentPosition == limit) {
            resetState();

            token.tokenType = Tokens.X_ENDPARSE;

            return;
        }

        if (scanSeparator()) {

//            token.isDelimiter = true;
        }

        if (currentPosition == limit) {
            resetState();

            token.tokenType = Tokens.X_ENDPARSE;

            return;
        }

        boolean needsDelimiter = !token.isDelimiter;

        scanToken();

        if (needsDelimiter && !token.isDelimiter) {

//            token.tokenType = Token.X_UNKNOWN_TOKEN;
        }

        if (token.isMalformed) {
            token.fullString = getPart(tokenPosition, currentPosition);
        }
    }
