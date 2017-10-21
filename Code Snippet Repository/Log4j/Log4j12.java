    private Map<String, String> buildClassToPropertyPrefixMap() {
        final String prefix = "log4j.appender.";
        final int preLength = prefix.length();
        final Map<String, String> map = new HashMap<>();
        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            final Object keyObj = entry.getKey();
            if (keyObj != null) {
                final String key = keyObj.toString();
                if (key.startsWith(prefix)) {
                    if (key.indexOf('.', preLength) < 0) {
                        final String name = key.substring(preLength);
                        final Object value = entry.getValue();
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                }
            }
        }
        return map;
    }
