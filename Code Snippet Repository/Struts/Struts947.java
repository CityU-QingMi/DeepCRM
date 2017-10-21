    private Object findValueOnStack(final String key) {
        if ("parameters".equals(key)) {
            if (parametersCache != null) {
                return parametersCache;
            }
            Object parametersLocal = stack.findValue(key);
            parametersCache = parametersLocal;
            return parametersLocal;
        }
        return stack.findValue(key);
    }
