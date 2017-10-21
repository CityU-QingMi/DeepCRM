    static ThreadLocal<Map<String, String>> createThreadLocalMap(final boolean isMapEnabled) {
        final PropertiesUtil managerProps = PropertiesUtil.getProperties();
        final boolean inheritable = managerProps.getBooleanProperty(INHERITABLE_MAP);
        if (inheritable) {
            return new InheritableThreadLocal<Map<String, String>>() {
                @Override
                protected Map<String, String> childValue(final Map<String, String> parentValue) {
                    return parentValue != null && isMapEnabled //
                    ? Collections.unmodifiableMap(new HashMap<>(parentValue)) //
                            : null;
                }
            };
        }
        // if not inheritable, return plain ThreadLocal with null as initial value
        return new ThreadLocal<>();
    }
