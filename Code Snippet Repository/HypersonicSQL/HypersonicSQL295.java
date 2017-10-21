    String getStatement(int startPosition, short[] startTokens) {

        while (true) {
            if (token.tokenType == Tokens.SEMICOLON) {
                break;
            } else if (token.tokenType == Tokens.X_ENDPARSE) {
                break;
            } else {
                if (ArrayUtil.find(startTokens, token.tokenType) != -1) {
                    break;
                }
            }

            read();
        }

        String sql = scanner.getPart(startPosition,
                                     scanner.getTokenPosition());

        return sql;
    }
