    private static CronScheduleBuilder cronScheduleNoParseException(
            String presumedValidCronExpression) {
        try {
            return cronSchedule(new CronExpression(presumedValidCronExpression));
        } catch (ParseException e) {
            // all methods of construction ensure the expression is valid by
            // this point...
            throw new RuntimeException(
                    "CronExpression '"
                            + presumedValidCronExpression
                            + "' is invalid, which should not be possible, please report bug to Quartz developers.",
                    e);
        }
    }
