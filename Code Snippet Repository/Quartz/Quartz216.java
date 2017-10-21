    public boolean interrupt(String fireInstanceId) throws UnableToInterruptJobException {
        try {
            return (Boolean)invoke(
                    "interruptJob",
                    new Object[] { fireInstanceId },
                    new String[] { String.class.getName() });
        } catch (SchedulerException se) {
            throw new UnableToInterruptJobException(se);
        }
    }
