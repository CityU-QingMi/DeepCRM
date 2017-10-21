    @Test
    public void testEscapeLayout() throws Exception {
        final Map<String, Appender> appenders = this.rootLogger.getAppenders();
        for (final Appender appender : appenders.values()) {
            this.rootLogger.removeAppender(appender);
        }
        final Configuration configuration = rootLogger.getContext().getConfiguration();
        // set up appender
        final boolean propertiesAsList = false;
        // @formatter:off
        final AbstractJacksonLayout layout = JsonLayout.newBuilder()
                .setConfiguration(configuration)
                .setLocationInfo(true)
                .setProperties(true)
                .setPropertiesAsList(propertiesAsList)
                .setComplete(true)
                .setCompact(false)
                .setEventEol(false)
                .setIncludeStacktrace(true)
                .build();
        // @formatter:on
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        // set appender on root and set level to debug
        this.rootLogger.addAppender(appender);
        this.rootLogger.setLevel(Level.DEBUG);

        // output starting message
        this.rootLogger.debug("Here is a quote ' and then a double quote \"");

        appender.stop();

        final List<String> list = appender.getMessages();

        this.checkAt("[", 0, list);
        this.checkAt("{", 1, list);
        this.checkContains("\"level\" : \"DEBUG\",", list);
        this.checkContains("\"message\" : \"Here is a quote ' and then a double quote \\\"\",", list);
        this.checkContains("\"loggerFqcn\" : \"" + AbstractLogger.class.getName() + "\",", list);
        for (final Appender app : appenders.values()) {
            this.rootLogger.addAppender(app);
        }
    }
