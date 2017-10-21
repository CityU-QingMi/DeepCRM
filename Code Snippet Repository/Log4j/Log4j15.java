    private void buildDailyRollingFileAppender(final String appenderName) {
        final AppenderComponentBuilder appenderBuilder = builder.newAppender(appenderName,
                RollingFileAppender.PLUGIN_NAME);
        buildFileAppender(appenderName, appenderBuilder);
        final String fileName = getLog4jAppenderValue(appenderName, "File");
        final String datePattern = getLog4jAppenderValue(appenderName, "DatePattern", fileName + "'.'yyyy-MM-dd");
        appenderBuilder.addAttribute("filePattern", fileName + "%d{" + datePattern + "}");
        final ComponentBuilder<?> triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy").addAttribute("modulate", true));
        appenderBuilder.addComponent(triggeringPolicy);
        appenderBuilder
                .addComponent(builder.newComponent("DefaultRolloverStrategy").addAttribute("max", Integer.MAX_VALUE));
        builder.add(appenderBuilder);
    }
