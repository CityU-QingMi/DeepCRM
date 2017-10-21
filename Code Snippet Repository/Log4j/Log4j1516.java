    public static boolean isValidExpression(final String cronExpression) {

        try {
            new CronExpression(cronExpression);
        } catch (final ParseException pe) {
            return false;
        }

        return true;
    }
