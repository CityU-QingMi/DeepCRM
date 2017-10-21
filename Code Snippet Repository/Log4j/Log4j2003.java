    @Test
    public void test() {
        final Configuration configuration = context.getConfiguration();
        assertNotNull(configuration);
        final RollingFileAppender appender = configuration.getAppender("File");
        assertNotNull(appender);
        final CompositeTriggeringPolicy compositeTriggeringPolicy = appender.getTriggeringPolicy();
        assertNotNull(compositeTriggeringPolicy);
        final TriggeringPolicy[] triggeringPolicies = compositeTriggeringPolicy.getTriggeringPolicies();
        SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy = null;
        TimeBasedTriggeringPolicy timeBasedTriggeringPolicy = null;
        for (final TriggeringPolicy triggeringPolicy : triggeringPolicies) {
            if (triggeringPolicy instanceof TimeBasedTriggeringPolicy) {
                timeBasedTriggeringPolicy = (TimeBasedTriggeringPolicy) triggeringPolicy;
                assertEquals(7, timeBasedTriggeringPolicy.getInterval());
            }
            if (triggeringPolicy instanceof SizeBasedTriggeringPolicy) {
                sizeBasedTriggeringPolicy = (SizeBasedTriggeringPolicy) triggeringPolicy;
                assertEquals(100 * 1024 * 1024, sizeBasedTriggeringPolicy.getMaxFileSize());
            }
        }
        if (timeBasedTriggeringPolicy == null) {
            fail("Missing TimeBasedTriggeringPolicy");
        }
        if (sizeBasedTriggeringPolicy == null) {
            fail("Missing SizeBasedTriggeringPolicy");
        }
    }
