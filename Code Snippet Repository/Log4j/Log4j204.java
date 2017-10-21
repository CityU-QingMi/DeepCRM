    private static int getMajorVersion() {
        final String version = System.getProperty("java.version");
        final String[] parts = version.split("-|\\.");
        boolean isJEP223;
        try {
            final int token = Integer.parseInt(parts[0]);
            isJEP223 = token != 1;
            if (isJEP223) {
                return token;
            }
            return Integer.parseInt(parts[1]);
        } catch (final Exception ex) {
            return 0;
        }
    }
