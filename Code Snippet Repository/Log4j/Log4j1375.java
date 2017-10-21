    public static Map<String, String> createMap(final String[] values, final String[] dontEscapeKeys) {
        final String[] sortedIgnoreKeys = dontEscapeKeys != null ? dontEscapeKeys.clone() : new String[0];
        Arrays.sort(sortedIgnoreKeys);
        final Map<String, String> map = new HashMap<>();
        for (final String string : values) {
            final String[] keyValue = string.split(Patterns.toWhitespaceSeparator("="));
            if (keyValue.length > 1) {
                final String key = keyValue[0].toUpperCase(Locale.ENGLISH);
                final String value = keyValue[1];
                final boolean escape = Arrays.binarySearch(sortedIgnoreKeys, key) < 0;
                map.put(key, escape ? createSequence(value.split("\\s")) : value);
            }
        }
        return map;
    }
