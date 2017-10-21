    @PluginFactory
    public static CronTriggeringPolicy createPolicy(@PluginConfiguration final Configuration configuration,
            @PluginAttribute("evaluateOnStartup") final String evaluateOnStartup,
            @PluginAttribute("schedule") final String schedule) {
        CronExpression cronExpression;
        final boolean checkOnStartup = Boolean.parseBoolean(evaluateOnStartup);
        if (schedule == null) {
            LOGGER.info("No schedule specified, defaulting to Daily");
            cronExpression = getSchedule(defaultSchedule);
        } else {
            cronExpression = getSchedule(schedule);
            if (cronExpression == null) {
                LOGGER.error("Invalid expression specified. Defaulting to Daily");
                cronExpression = getSchedule(defaultSchedule);
            }
        }
        return new CronTriggeringPolicy(cronExpression, checkOnStartup, configuration);
    }
