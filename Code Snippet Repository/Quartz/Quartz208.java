    public SchedulerMetaData getMetaData() throws SchedulerException {
        AttributeList attributeList =
            getAttributes(
                new String[] {
                    "SchedulerName",
                    "SchedulerInstanceId",
                    "StandbyMode",
                    "Shutdown",
                    "JobStoreClassName",
                    "ThreadPoolClassName",
                    "ThreadPoolSize",
                    "Version",
                    "PerformanceMetrics"
                });

        try {
            return new SchedulerMetaData(
                    (String)getAttribute(attributeList, 0).getValue(),
                    (String)getAttribute(attributeList, 1).getValue(),
                    getClass(), true, false,
                    (Boolean)getAttribute(attributeList, 2).getValue(),
                    (Boolean)getAttribute(attributeList, 3).getValue(),
                    null,
                    Integer.parseInt(((Map)getAttribute(attributeList, 8).getValue()).get("JobsExecuted").toString()),
                    Class.forName((String)getAttribute(attributeList, 4).getValue()),
                    false,
                    false,
                    Class.forName((String)getAttribute(attributeList, 5).getValue()),
                    (Integer)getAttribute(attributeList, 6).getValue(),
                    (String)getAttribute(attributeList, 7).getValue());
        } catch (ClassNotFoundException e) {
            throw new SchedulerException(e);
        }
    }
