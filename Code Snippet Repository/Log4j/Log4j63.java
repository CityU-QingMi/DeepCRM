    public static Level forName(final String name, final int intValue) {
        final Level level = LEVELS.get(name);
        if (level != null) {
            return level;
        }
        try {
            return new Level(name, intValue);
        } catch (final IllegalStateException ex) {
            // The level was added by something else so just return that one.
            return LEVELS.get(name);
        }
    }
