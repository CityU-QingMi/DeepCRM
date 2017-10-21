    protected Builder newSyslogAppenderBuilder(final String protocol, final String format, final boolean newLine) {
        // @formatter:off
        return SyslogAppender.newSyslogAppenderBuilder()
                .withPort(PORTNUM)
                .withProtocol(EnglishEnums.valueOf(Protocol.class, protocol))
                .withReconnectDelayMillis(-1)
                .withName("TestApp")
                .withIgnoreExceptions(false)
                .setId("Audit")
                .setEnterpriseNumber(18060)
                .setMdcId("RequestContext")
                .setNewLine(newLine)
                .setAppName("TestApp")
                .setMsgId("Test")
                .setFormat(format);
        // @formatter:on
    }
