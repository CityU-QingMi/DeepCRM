    public JobDetail getJobDetail(JobKey jobKey) throws SchedulerException {
        try {
            return JobDetailSupport.newJobDetail((CompositeData)invoke(
                    "getJobDetail",
                    new Object[] { jobKey.getName(), jobKey.getGroup() },
                    new String[] { String.class.getName(), String.class.getName() }));
        } catch (ClassNotFoundException e) {
            throw new SchedulerException("Unable to resolve job class", e);
        }
    }
