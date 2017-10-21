    private static ConcurrentMap<String, Logger> getLoggersMap(final LoggerContext context) {
        synchronized (CONTEXT_MAP) {
            ConcurrentMap<String, Logger> map = CONTEXT_MAP.get(context);
            if (map == null) {
                map = new ConcurrentHashMap<>();
                CONTEXT_MAP.put(context, map);
            }
            return map;
        }
    }
