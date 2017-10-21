    public static TabularData toTabularData(JobDataMap jobDataMap) {
        TabularData tData = new TabularDataSupport(TABULAR_TYPE);
        ArrayList<CompositeData> list = new ArrayList<CompositeData>();
        Iterator<String> iter = jobDataMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            list.add(toCompositeData(key, String.valueOf(jobDataMap.get(key))));
        }
        tData.putAll(list.toArray(new CompositeData[list.size()]));
        return tData;
    }
