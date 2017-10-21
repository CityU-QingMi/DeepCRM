    String getStatementForRoutine(int startPosition, short[] startTokens) {

        int tokenIndex   = 0;
        int semiIndex    = -1;
        int semiPosition = -1;

        while (true) {
            if (token.tokenType == Tokens.SEMICOLON) {
                semiPosition = scanner.getTokenPosition();
                semiIndex    = tokenIndex;
            } else if (token.tokenType == Tokens.X_ENDPARSE) {
                if (semiIndex > 0 && semiIndex == tokenIndex - 1) {
                    rewind(semiPosition);
                }

                break;
            } else {
                if (ArrayUtil.find(startTokens, token.tokenType) != -1) {
                    break;
                }
            }

            read();

            tokenIndex++;
        }

        String sql = scanner.getPart(startPosition,
                                     scanner.getTokenPosition());

        return sql;
    }
