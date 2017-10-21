    public static void putAll(final Map<String, String> m) {
        if (contextMap instanceof ThreadContextMap2) {
            ((ThreadContextMap2) contextMap).putAll(m);
        } else if (contextMap instanceof DefaultThreadContextMap) {
            ((DefaultThreadContextMap) contextMap).putAll(m);
        } else {
            for (final Map.Entry<String, String> entry: m.entrySet()) {
                contextMap.put(entry.getKey(), entry.getValue());
            }
        }
    }
