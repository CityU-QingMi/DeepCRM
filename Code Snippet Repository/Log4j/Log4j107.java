    static void formatMessage3(final StringBuilder buffer, final char[] messagePattern, final int patternLength,
            final Object[] arguments, final int argCount, final int[] indices) {
        if (messagePattern == null) {
            return;
        }
        if (arguments == null || argCount == 0) {
            buffer.append(messagePattern);
            return;
        }
        int previous = 0;
        for (int i = 0; i < argCount; i++) {
            buffer.append(messagePattern, previous, indices[i]);
            previous = indices[i] + 2;
            recursiveDeepToString(arguments[i], buffer, null);
        }
        buffer.append(messagePattern, previous, patternLength);
    }
