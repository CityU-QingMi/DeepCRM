    private boolean scanUnicodeIdentifier() {

        int escape = '\\';

        scanStringPart('"');

        if (token.isMalformed) {
            return false;
        }

        token.tokenString = charWriter.toString();

        int c = charAt(currentPosition);

        if (c == 'u' || c == 'U') {
            if (scanSpecialIdentifier(Tokens.T_UESCAPE)) {
                scanSeparator();

                escape = scanEscapeDefinition();

                if (escape == -1) {
                    token.tokenType   = Tokens.X_MALFORMED_UNICODE_ESCAPE;
                    token.isMalformed = true;

                    return false;
                }
            }
        }

        convertUnicodeString(escape);

        return !token.isMalformed;
    }
