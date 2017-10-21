    static Configuration addTestFixtures(final String name, final ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.ERROR);
        builder.add(builder.newScriptFile("target/test-classes/scripts/filter.groovy").addIsWatched(true));
        builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                .addAttribute("level", Level.DEBUG));

        final AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout").
                addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        appenderBuilder.add(builder.newFilter("MarkerFilter", Filter.Result.DENY,
                Filter.Result.NEUTRAL).addAttribute("marker", "FLOW"));
        builder.add(appenderBuilder);

        final AppenderComponentBuilder appenderBuilder2 = builder.newAppender("Kafka", "Kafka").addAttribute("topic", "my-topic");
        appenderBuilder2.addComponent(builder.newProperty("bootstrap.servers", "localhost:9092"));
        appenderBuilder2.add(builder.newLayout("GelfLayout").
            addAttribute("host", "my-host").
            addComponent(builder.newKeyValuePair("extraField", "extraValue")));
        builder.add(appenderBuilder2);

        builder.add(builder.newLogger("org.apache.logging.log4j", Level.DEBUG, true).
                    add(builder.newAppenderRef("Stdout")).
                    addAttribute("additivity", false));
        builder.add(builder.newRootLogger(Level.ERROR).add(builder.newAppenderRef("Stdout")));

        builder.add(builder.newCustomLevel("Panic", 17));

        return builder.build();
    }
