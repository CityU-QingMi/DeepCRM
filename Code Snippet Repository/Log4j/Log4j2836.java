    @Setup
    public void setUp() {
        System.setProperty("log4j2.enable.direct.encoders", "true");

        appender = new DemoAppender(GelfLayout.newBuilder()
                .setConfiguration(new NullConfiguration())
                .setHost("host")
                .setAdditionalFields(ADDITIONAL_FIELDS)
                .setCompressionType(GelfLayout.CompressionType.OFF)
                .setCompressionThreshold(0)
                .setIncludeStacktrace(true)
                .setIncludeThreadContext(true)
                .build());

        j = 0;
    }
