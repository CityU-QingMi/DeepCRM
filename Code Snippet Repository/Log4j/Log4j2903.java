    private String[] argumentsToStrings(final Object[] arguments) {
        if (arguments == null) {
            return null;
        }
        final int argsCount = countArgumentPlaceholders(messagePattern);
        int resultArgCount = arguments.length;
        if (argsCount < arguments.length && throwable == null && arguments[arguments.length - 1] instanceof Throwable) {
            throwable = (Throwable) arguments[arguments.length - 1];
            resultArgCount--;
        }
        argArray = new Object[resultArgCount];
        System.arraycopy(arguments, 0, argArray, 0, resultArgCount);

        String[] strArgs;
        if (argsCount == 1 && throwable == null && arguments.length > 1) {
            // special case
            strArgs = new String[1];
            strArgs[0] = deepToString(arguments);
        } else {
            strArgs = new String[resultArgCount];
            for (int i = 0; i < strArgs.length; i++) {
                strArgs[i] = deepToString(arguments[i]);
            }
        }
        return strArgs;
    }
