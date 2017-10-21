    protected static String removeAttributeValue(final Map<String, String> attributes,
                                                 final String name,
                                                 final String... aliases) {
        for (final Map.Entry<String, String> entry : attributes.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            if (key.equalsIgnoreCase(name)) {
                attributes.remove(key);
                return value;
            }
            if (aliases != null) {
                for (final String alias : aliases) {
                    if (key.equalsIgnoreCase(alias)) {
                        attributes.remove(key);
                        return value;
                    }
                }
            }
        }
        return null;
    }
