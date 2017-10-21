    public boolean scanWhitespace() {

        boolean result = false;

        for (; currentPosition < limit; currentPosition++) {
            char c = sqlString.charAt(currentPosition);

            if (c == ' ') {
                result = true;

                continue;
            }

            if (whiteSpaceSet.contains(c)) {
                hasNonSpaceSeparator = true;
                result               = true;

                setLineNumber(c);

                continue;
            }

            break;
        }

        return result;
    }
