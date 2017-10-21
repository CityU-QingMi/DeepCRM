    protected void addContextData(final String prefix, final Map<String, String> fields,
                                  final Map<String, String> context) {
        final Map<String, String> map = new HashMap<>();
        for (final Map.Entry<String, String> entry : context.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                fields.put(prefix + entry.getKey(), entry.getValue());
                map.put(prefix + entry.getKey(), entry.getValue());
            }
        }
        context.clear();
        context.putAll(map);
    }
