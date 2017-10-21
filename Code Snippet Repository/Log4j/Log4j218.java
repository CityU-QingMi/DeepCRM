    public static Map<String, Properties> partitionOnCommonPrefixes(final Properties properties) {
        final Map<String, Properties> parts = new ConcurrentHashMap<>();
        for (final String key : properties.stringPropertyNames()) {
            final String prefix = key.substring(0, key.indexOf('.'));
            if (!parts.containsKey(prefix)) {
                parts.put(prefix, new Properties());
            }
            parts.get(prefix).setProperty(key.substring(key.indexOf('.') + 1), properties.getProperty(key));
        }
        return parts;
    }
