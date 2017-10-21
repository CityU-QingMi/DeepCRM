    public static JobDataMap newJobDataMap(Map<String, Object> map) {
        JobDataMap jobDataMap = new JobDataMap();

        if(map != null) {
            for (final Iterator<String> pos = map.keySet().iterator(); pos.hasNext();) {
                String key = pos.next();
                jobDataMap.put(key, map.get(key));
            }
        }
        
        return jobDataMap;
    }
