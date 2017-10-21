    public static TabularData toTabularData(JobDetail[] jobDetails) {
        TabularData tData = new TabularDataSupport(TABULAR_TYPE);
        if (jobDetails != null) {
            ArrayList<CompositeData> list = new ArrayList<CompositeData>();
            for (JobDetail jobDetail : jobDetails) {
                list.add(toCompositeData(jobDetail));
            }
            tData.putAll(list.toArray(new CompositeData[list.size()]));
        }
        return tData;
    }
