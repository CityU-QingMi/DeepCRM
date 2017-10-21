    public static LevelPatternConverter newInstance(final String[] options) {
        if (options == null || options.length == 0) {
            return INSTANCE;
        }
        final Map<Level, String> levelMap = new HashMap<>();
        int length = Integer.MAX_VALUE; // More than the longest level name.
        boolean lowerCase = false;
        final String[] definitions = options[0].split(Patterns.COMMA_SEPARATOR);
        for (final String def : definitions) {
            final String[] pair = def.split("=");
            if (pair == null || pair.length != 2) {
                LOGGER.error("Invalid option {}", def);
                continue;
            }
            final String key = pair[0].trim();
            final String value = pair[1].trim();
            if (OPTION_LENGTH.equalsIgnoreCase(key)) {
                length = Integer.parseInt(value);
            } else if (OPTION_LOWER.equalsIgnoreCase(key)) {
                lowerCase = Boolean.parseBoolean(value);
            } else {
                final Level level = Level.toLevel(key, null);
                if (level == null) {
                    LOGGER.error("Invalid Level {}", key);
                } else {
                    levelMap.put(level, value);
                }
            }
        }
        if (levelMap.isEmpty() && length == Integer.MAX_VALUE && !lowerCase) {
            return INSTANCE;
        }
        for (final Level level : Level.values()) {
            if (!levelMap.containsKey(level)) {
                final String left = left(level, length);
                levelMap.put(level, lowerCase ? left.toLowerCase(Locale.US) : left);
            }
        }
        return new LevelPatternConverter(levelMap);
    }
