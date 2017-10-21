    public static String getProcessId() {
        try {
            return ManagementFactory.getRuntimeMXBean().getName().split("@")[0]; // likely works on most platforms
        } catch (final Exception ex) {
            try {
                return new File("/proc/self").getCanonicalFile().getName(); // try a Linux-specific way
            } catch (final IOException ignoredUseDefault) {
                // Ignore exception.
            }
        }
        return DEFAULT_PROCESSID;
    }
