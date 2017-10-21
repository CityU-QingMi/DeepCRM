    private long deref(String varName) {
        if (!vars.containsKey(varName))
            throw new IllegalStateException("Undefined variable: " + varName);
        try {
            return Long.parseLong(vars.get(varName));
        } catch (NumberFormatException nfe) {
            throw new IllegalStateException(
                    "Variable's value not an integer: " + varName);
        }
    }
