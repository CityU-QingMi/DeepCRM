    public void scanStringPart(char quoteChar) {

        currentPosition++;

        for (;;) {
            int nextIndex = sqlString.indexOf(quoteChar, currentPosition);

            if (nextIndex < 0) {
                token.tokenString = sqlString.substring(currentPosition,
                        limit);
                token.tokenType = quoteChar == '\'' ? Tokens.X_MALFORMED_STRING
                                                    : Tokens
                                                    .X_MALFORMED_IDENTIFIER;
                token.isMalformed = true;

                return;
            }

            if (charAt(nextIndex + 1) == quoteChar) {
                nextIndex += 1;

                charWriter.write(sqlString, currentPosition,
                                 nextIndex - currentPosition);

                currentPosition = nextIndex + 1;

                continue;
            } else {
                charWriter.write(sqlString, currentPosition,
                                 nextIndex - currentPosition);

                currentPosition = nextIndex + 1;

                break;
            }
        }
    }
