    private ConfigurationBuilder<BuiltConfiguration> buildConfigurationBuilder(final String filePattern) {
        final ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setConfigurationName("LOG4J2-1964 demo");
        builder.setStatusLevel(Level.ERROR);
        // @formatter:off
        builder.add(builder.newAppender("consoleLog", "Console")
            .addAttribute("target", ConsoleAppender.Target.SYSTEM_ERR));
        builder.add(builder.newAppender("fooAppender", "RollingFile")
                .addAttribute("fileName", "target/rolling-update-date/foo.log")
                .addAttribute("filePattern", filePattern)
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
                        .addAttribute("size", "10MB")));
        builder.add(builder.newRootLogger(Level.INFO)
                .add(builder.newAppenderRef("consoleLog"))
                .add(builder.newAppenderRef("fooAppender")));
        // @formatter:on
        return builder;
    }
