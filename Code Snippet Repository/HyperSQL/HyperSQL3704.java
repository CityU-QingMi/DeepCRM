    boolean scanCommentAsInlineSeparator() {

        int character = charAt(currentPosition);

        if (character == '-' && charAt(currentPosition + 1) == '-') {
            int pos = sqlString.indexOf('\r', currentPosition + 2);

            if (pos == -1) {
                pos = sqlString.indexOf('\n', currentPosition + 2);
            } else if (charAt(pos + 1) == '\n') {
                pos++;
            }

            if (pos == -1) {
                currentPosition = limit;
            } else {
                currentPosition = pos + 1;
            }

            return true;
        } else if (character == '/' && charAt(currentPosition + 1) == '*') {
            int pos = sqlString.indexOf("*/", currentPosition + 2);

            if (pos == -1) {
                token.tokenString = sqlString.substring(currentPosition,
                        currentPosition + 2);
                token.tokenType   = Tokens.X_MALFORMED_COMMENT;
                token.isMalformed = true;

                return false;
            }

            currentPosition = pos + 2;

            return true;
        }

        return false;
    }
