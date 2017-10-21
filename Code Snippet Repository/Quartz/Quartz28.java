    public static boolean isValidExpression(String cronExpression) {
        
        try {
            new CronExpression(cronExpression);
        } catch (ParseException pe) {
            return false;
        }
        
        return true;
    }
