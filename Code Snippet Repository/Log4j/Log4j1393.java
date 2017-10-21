    private static Map<Level, String> createLevelStyleMap(final String[] options) {
        if (options.length < 2) {
            return DEFAULT_STYLES;
        }
        // Feels like a hack. Should String[] options change to a Map<String,String>?
        final String string = options[1]
                .replaceAll(PatternParser.DISABLE_ANSI + "=(true|false)", Strings.EMPTY)
                .replaceAll(PatternParser.NO_CONSOLE_NO_ANSI + "=(true|false)", Strings.EMPTY);
        //
        final Map<String, String> styles = AnsiEscape.createMap(string, new String[] {STYLE_KEY});
        final Map<Level, String> levelStyles = new HashMap<>(DEFAULT_STYLES);
        for (final Map.Entry<String, String> entry : styles.entrySet()) {
            final String key = entry.getKey().toUpperCase(Locale.ENGLISH);
            final String value = entry.getValue();
            if (STYLE_KEY.equalsIgnoreCase(key)) {
                final Map<Level, String> enumMap = STYLES.get(value.toUpperCase(Locale.ENGLISH));
                if (enumMap == null) {
                    LOGGER.error("Unknown level style: " + value + ". Use one of " +
                        Arrays.toString(STYLES.keySet().toArray()));
                } else {
                    levelStyles.putAll(enumMap);
                }
            } else {
                final Level level = Level.toLevel(key, null);
                if (level == null) {
                    LOGGER.error("Unknown level name: {}; use one of {}", key, Arrays.toString(Level.values()));
                } else {
                    levelStyles.put(level, value);
                }
            }
        }
        return levelStyles;
    }
