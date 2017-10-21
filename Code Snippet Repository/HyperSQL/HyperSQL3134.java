    private static int MAX_CHAR_OR_VARCHAR_DISPLAY_SIZE() {

        try {
            return Integer.getInteger(
                HsqlDatabaseProperties.system_max_char_or_varchar_display_size,
                32766).intValue();
        } catch (SecurityException e) {
            return 32766;
        }
    }
