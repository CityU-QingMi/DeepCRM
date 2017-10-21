    public static ArrayList<String> generateMessages(final int numberOfMessages, final TlsSyslogMessageFormat format) {
        switch (format) {
            case SYSLOG:
                return generateMessages(numberOfMessages, SYSLOG_CHARSET);
            case LEGACY_BSD:
                return generateMessages(numberOfMessages, LEGACY_BSD_SYSLOG_CHARSET);
            default:
                throw new IllegalArgumentException();
        }
    }
