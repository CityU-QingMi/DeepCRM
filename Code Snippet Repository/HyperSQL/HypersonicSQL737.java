    void scanBitStringPart(BitMap map) {

        boolean complete = false;
        int     bitIndex = map.size();

        currentPosition++;

        for (; currentPosition < limit; currentPosition++) {
            char c = sqlString.charAt(currentPosition);

            if (c == ' ') {
                continue;
            }

            if (c == '\'') {
                complete = true;

                currentPosition++;

                break;
            }

            if (c == '0') {
                map.unset(bitIndex);

                bitIndex++;
            } else if (c == '1') {
                map.set(bitIndex);

                bitIndex++;
            } else {
                token.tokenType   = Tokens.X_MALFORMED_BIT_STRING;
                token.isMalformed = true;

                return;
            }
        }

        if (!complete) {
            token.tokenType   = Tokens.X_MALFORMED_BIT_STRING;
            token.isMalformed = true;

            return;
        }

        map.setSize(bitIndex);
    }
