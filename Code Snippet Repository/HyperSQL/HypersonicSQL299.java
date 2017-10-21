    String readQuotedString() {

        checkIsValue();

        if (!token.dataType.isCharacterType()) {
            throw Error.error(ErrorCode.X_42563);
        }

        String value = token.tokenString;

        read();

        return value;
    }
