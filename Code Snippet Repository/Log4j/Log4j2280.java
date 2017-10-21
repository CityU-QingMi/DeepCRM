    private TlsSyslogInputStreamReaderBase createTLSSyslogReader(final InputStream inputStream) {
        switch (messageFormat) {
            case SYSLOG:
                return new TlsSyslogInputStreamReader(inputStream);
            case LEGACY_BSD:
                return new LegacyBsdTlsSyslogInputStreamReader(inputStream);
            default:
                return null;
        }
    }
