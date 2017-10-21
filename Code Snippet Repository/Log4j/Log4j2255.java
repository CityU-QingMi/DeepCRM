    @Test
    public void testEscapeLayout() throws Exception {
        final Map<String, Appender> appenders = this.rootLogger.getAppenders();
        for (final Appender appender : appenders.values()) {
            this.rootLogger.removeAppender(appender);
        }
        final Configuration configuration = rootLogger.getContext().getConfiguration();
        // set up appender
        final AbstractJacksonLayout layout = YamlLayout.newBuilder()
                .setLocationInfo(true)
                .setProperties(true)
                .setIncludeStacktrace(true)
                .setConfiguration(configuration)
                .build();

        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        // set appender on root and set level to debug
        this.rootLogger.addAppender(appender);
        this.rootLogger.setLevel(Level.DEBUG);

        // output starting message
        this.rootLogger.debug("Here is a quote ' and then a double quote \"");

        appender.stop();

        final List<String> list = appender.getMessages();

        this.checkAt("---", 0, list);
        this.checkContains("level: \"DEBUG\"", list);
        this.checkContains("message: \"Here is a quote ' and then a double quote \\\"\"", list);
        this.checkContains("loggerFqcn: \"" + AbstractLogger.class.getName() + "\"", list);
        for (final Appender app : appenders.values()) {
            this.rootLogger.addAppender(app);
        }
    }
