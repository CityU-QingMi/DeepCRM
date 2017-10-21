    @Override
    public MutableTrigger build() {

        CronTriggerImpl ct = new CronTriggerImpl();

        ct.setCronExpression(cronExpression);
        ct.setTimeZone(cronExpression.getTimeZone());
        ct.setMisfireInstruction(misfireInstruction);

        return ct;
    }
