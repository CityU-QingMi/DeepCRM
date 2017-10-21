    private static String format0_inlined22(final String messagePattern, final int len, final String[] arguments) {
        final char[] result = new char[len + sumStringLengths(arguments)];
        int pos = 0;
        int escapeCounter = 0;
        int currentArgument = 0;
        int i = 0;
        for (; i < len - 1; i++) {
            final char curChar = messagePattern.charAt(i);
            if (curChar == ESCAPE_CHAR) {
                escapeCounter++;
            } else {
                if (isDelimPair(messagePattern, i, curChar)) {
                    i++;

                    // write escaped escape chars
                    pos = format0_writeEscapedEscapeChars(escapeCounter, result, pos);

                    if (isOdd(escapeCounter)) {
                        // i.e. escaped
                        // write escaped escape chars
                        pos = format0_writeDelimPair(result, pos);
                    } else {
                        // unescaped
                        pos = format0_appendArg(arguments, currentArgument, result, pos);
                        currentArgument++;
                    }
                } else {
                    pos = format0_handleLiteralChar(result, pos, escapeCounter, curChar);
                }
                escapeCounter = 0;
            }
        }
        pos = format0_handleMaybeLastChar(messagePattern, len, result, pos, escapeCounter, i);
        return new String(result, 0, pos);
    }
