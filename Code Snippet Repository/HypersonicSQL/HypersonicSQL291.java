    HsqlException unexpectedTokenRequire(String required) {

        if (token.tokenType == Tokens.X_ENDPARSE) {
            return Error.parseError(ErrorCode.X_42590,
                                    ErrorCode.TOKEN_REQUIRED,
                                    scanner.getLineNumber(), new Object[] {
                "", required
            });
        }

        String tokenS;

        if (token.charsetSchema != null) {
            tokenS = token.charsetSchema;
        } else if (token.charsetName != null) {
            tokenS = token.charsetName;
        } else if (token.namePrePrefix != null) {
            tokenS = token.namePrePrefix;
        } else if (token.namePrefix != null) {
            tokenS = token.namePrefix;
        } else {
            tokenS = token.tokenString;
        }

        return Error.parseError(ErrorCode.X_42581, ErrorCode.TOKEN_REQUIRED,
                                scanner.getLineNumber(), new Object[] {
            tokenS, required
        });
    }
