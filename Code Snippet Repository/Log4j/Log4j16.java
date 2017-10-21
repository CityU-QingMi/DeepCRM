    private void buildRollingFileAppender(final String appenderName) {
        final AppenderComponentBuilder appenderBuilder = builder.newAppender(appenderName,
                RollingFileAppender.PLUGIN_NAME);
        buildFileAppender(appenderName, appenderBuilder);
        final String fileName = getLog4jAppenderValue(appenderName, "File");
        appenderBuilder.addAttribute("filePattern", fileName + ".%i");
        final String maxFileSizeString = getLog4jAppenderValue(appenderName, "MaxFileSize", "10485760");
        final String maxBackupIndexString = getLog4jAppenderValue(appenderName, "MaxBackupIndex", "1");
        final ComponentBuilder<?> triggeringPolicy = builder.newComponent("Policies").addComponent(
                builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", maxFileSizeString));
        appenderBuilder.addComponent(triggeringPolicy);
        appenderBuilder.addComponent(
                builder.newComponent("DefaultRolloverStrategy").addAttribute("max", maxBackupIndexString));
        builder.add(appenderBuilder);
    }
