    public static JobDetail newJobDetail(CompositeData cData)
      throws ClassNotFoundException
    {
        JobDetailImpl jobDetail = new JobDetailImpl();

        int i = 0;
        jobDetail.setName((String) cData.get(ITEM_NAMES[i++]));
        jobDetail.setGroup((String) cData.get(ITEM_NAMES[i++]));
        jobDetail.setDescription((String) cData.get(ITEM_NAMES[i++]));
        Class<?> jobClass = Class.forName((String) cData.get(ITEM_NAMES[i++]));
        @SuppressWarnings("unchecked")
        Class<? extends Job> jobClassTyped = (Class<? extends Job>)jobClass;
        jobDetail.setJobClass(jobClassTyped);
        jobDetail.setJobDataMap(JobDataMapSupport.newJobDataMap((TabularData) cData.get(ITEM_NAMES[i++])));
        jobDetail.setDurability((Boolean) cData.get(ITEM_NAMES[i++]));
        jobDetail.setRequestsRecovery((Boolean) cData.get(ITEM_NAMES[i++]));

        return jobDetail;
    }
