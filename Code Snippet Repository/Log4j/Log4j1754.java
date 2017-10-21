    private SyslogAppender createAppender() {
        String format;

        if (messageFormat == TlsSyslogMessageFormat.LEGACY_BSD) {
            format = "LEGACY_BSD";
        } else {
            format = "RFC5424";
        }

        return SyslogAppender.createAppender("localhost", PORTNUM, "SSL", sslConfiguration, 0, -1, true, "Test", true,
            false, Facility.LOCAL0, "Audit", 18060, true, "RequestContext", null, null, includeNewLine, null,
            "TestApp", "Test", null, "ipAddress,loginId", null, format, null, null, null, null, null, false);
    }
