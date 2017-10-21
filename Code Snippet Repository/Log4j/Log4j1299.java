    @Override
    public String lookup(final LogEvent event, final String key) {
        switch (key) {
        case "version":
            return "Java version " + getSystemProperty("java.version");
        case "runtime":
            return getRuntime();
        case "vm":
            return getVirtualMachine();
        case "os":
            return getOperatingSystem();
        case "hw":
            return getHardware();
        case "locale":
            return getLocale();
        default:
            throw new IllegalArgumentException(key);
        }
    }
