    public static TabularData toTabularData(
            final List<JobExecutionContext> executingJobs)
            throws SchedulerException {
        List<CompositeData> list = new ArrayList<CompositeData>();
        for (JobExecutionContext executingJob : executingJobs) {
            list.add(toCompositeData(executingJob));
        }
        TabularData td = new TabularDataSupport(TABULAR_TYPE);
        td.putAll(list.toArray(new CompositeData[list.size()]));
        return td;
    }
