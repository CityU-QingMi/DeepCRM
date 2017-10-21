    public static CompositeData toCompositeData(JobDetail jobDetail) {
        try {
            return new CompositeDataSupport(COMPOSITE_TYPE, ITEM_NAMES,
                    new Object[] {
                            jobDetail.getKey().getName(),
                            jobDetail.getKey().getGroup(),
                            jobDetail.getDescription(),
                            jobDetail.getJobClass().getName(),
                            JobDataMapSupport.toTabularData(jobDetail
                                    .getJobDataMap()), 
                            jobDetail.isDurable(),
                            jobDetail.requestsRecovery(), });
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }
