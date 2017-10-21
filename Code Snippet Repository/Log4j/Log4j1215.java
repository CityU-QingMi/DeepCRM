    private static boolean isPreJava8() {
        final String version = System.getProperty("java.version");
        final String[] parts = version.split("\\.");
        try {
            final int major = Integer.parseInt(parts[1]);
            return major < 8;
        } catch (final Exception ex) {
            return true;
        }
    }
