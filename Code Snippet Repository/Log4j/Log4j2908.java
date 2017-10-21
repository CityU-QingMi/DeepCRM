    public static String format(final String messagePattern, final Object[] arguments) {
        if (messagePattern == null || arguments == null || arguments.length == 0) {
            return messagePattern;
        }
        if (arguments instanceof String[]) {
            return formatStringArgs(messagePattern, (String[]) arguments);
        }
        final String[] stringArgs = new String[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            stringArgs[i] = String.valueOf(arguments[i]);
        }
        return formatStringArgs(messagePattern, stringArgs);
    }
