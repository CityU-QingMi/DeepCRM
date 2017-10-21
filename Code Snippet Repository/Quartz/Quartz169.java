    public static JobDataMap newJobDataMap(TabularData tabularData) {
        JobDataMap jobDataMap = new JobDataMap();

        if(tabularData != null) {
            for (final Iterator<?> pos = tabularData.values().iterator(); pos.hasNext();) {
                CompositeData cData = (CompositeData) pos.next();
                jobDataMap.put((String) cData.get("key"), (String) cData.get("value"));
            }
        }
        
        return jobDataMap;
    }
