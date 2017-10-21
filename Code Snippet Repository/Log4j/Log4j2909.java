    private static String formatStringArgs0(final String messagePattern, final int len, final String[] arguments) {
        final char[] result = new char[len + sumStringLengths(arguments)];
        int pos = 0;
        int escapeCounter = 0;
        int currentArgument = 0;
        int i = 0;
        for (; i < len - 1; i++) { // last char is excluded from the loop
            final char curChar = messagePattern.charAt(i);
            if (curChar == ESCAPE_CHAR) {
                escapeCounter++;
            } else {
                if (isDelimPair(curChar, messagePattern, i)) { // looks ahead one char
                    i++;

                    // write escaped escape chars
                    pos = writeEscapedEscapeChars(escapeCounter, result, pos);

                    if (isOdd(escapeCounter)) {
                        // i.e. escaped
                        // write escaped escape chars
                        pos = writeDelimPair(result, pos);
                    } else {
                        // unescaped
                        pos = writeArgOrDelimPair(arguments, currentArgument, result, pos);
                        currentArgument++;
                    }
                } else {
                    pos = handleLiteralChar(result, pos, escapeCounter, curChar);
                }
                escapeCounter = 0;
            }
        }
        pos = handleRemainingCharIfAny(messagePattern, len, result, pos, escapeCounter, i);
        return new String(result, 0, pos);
    }
