    public static CompositeData toCompositeData(JobExecutionContext jec)
            throws SchedulerException {
        try {
            return new CompositeDataSupport(COMPOSITE_TYPE, ITEM_NAMES,
                    new Object[] {
                            jec.getScheduler().getSchedulerName(),
                            jec.getTrigger().getKey().getName(),
                            jec.getTrigger().getKey().getGroup(),
                            jec.getJobDetail().getKey().getName(),
                            jec.getJobDetail().getKey().getGroup(),
                            JobDataMapSupport.toTabularData(jec
                                    .getMergedJobDataMap()),
                            jec.getTrigger().getCalendarName(),
                            jec.isRecovering(),
                            jec.getRefireCount(),
                            jec.getFireTime(), jec.getScheduledFireTime(),
                            jec.getPreviousFireTime(), jec.getNextFireTime(),
                            jec.getJobRunTime(),
                            jec.getFireInstanceId() });
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }
