    void scanCharacterString() {

        charWriter.reset(charBuffer);

        while (true) {
            scanStringPart('\'');

            if (token.isMalformed) {
                return;
            }

            if (scanSeparator() && charAt(currentPosition) == '\'') {
                continue;
            }

            break;
        }

        token.tokenString = charWriter.toString();
        token.tokenValue  = token.tokenString;
    }
