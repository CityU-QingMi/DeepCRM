    HsqlException tooManyIdentifiers() {

        String tokenS;

        if (token.namePrePrePrefix != null) {
            tokenS = token.namePrePrePrefix;
        } else if (token.namePrePrefix != null) {
            tokenS = token.namePrePrefix;
        } else if (token.namePrefix != null) {
            tokenS = token.namePrefix;
        } else {
            tokenS = token.tokenString;
        }

        return Error.parseError(ErrorCode.X_42551, tokenS,
                                scanner.getLineNumber());
    }
