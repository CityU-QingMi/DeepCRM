    private void scanUnicodeString() {

        int escape = '\\';

        scanCharacterString();
        scanSeparator();

        int c = charAt(currentPosition);

        if (c == 'u' || c == 'U') {
            if (scanSpecialIdentifier(Tokens.T_UESCAPE)) {
                scanSeparator();

                escape = scanEscapeDefinition();

                if (escape == -1) {
                    token.tokenType   = Tokens.X_MALFORMED_UNICODE_ESCAPE;
                    token.isMalformed = true;

                    return;
                }
            }
        }

        convertUnicodeString(escape);
    }
