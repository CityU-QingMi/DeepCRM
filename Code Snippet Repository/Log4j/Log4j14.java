    private void buildConsoleAppender(final String appenderName) {
        final AppenderComponentBuilder appenderBuilder = builder.newAppender(appenderName, ConsoleAppender.PLUGIN_NAME);
        final String targetValue = getLog4jAppenderValue(appenderName, "Target", "System.out");
        if (targetValue != null) {
            final ConsoleAppender.Target target;
            switch (targetValue) {
            case "System.out":
                target = ConsoleAppender.Target.SYSTEM_OUT;
                break;
            case "System.err":
                target = ConsoleAppender.Target.SYSTEM_ERR;
                break;
            default:
                reportWarning("Unknown value for console Target: " + targetValue);
                target = null;
            }
            if (target != null) {
                appenderBuilder.addAttribute("target", target);
            }
        }
        buildAttribute(appenderName, appenderBuilder, "Follow", "follow");
        if (FALSE.equalsIgnoreCase(getLog4jAppenderValue(appenderName, "ImmediateFlush"))) {
            reportWarning("ImmediateFlush=false is not supported on Console appender");
        }
        buildAppenderLayout(appenderName, appenderBuilder);
        builder.add(appenderBuilder);
    }
