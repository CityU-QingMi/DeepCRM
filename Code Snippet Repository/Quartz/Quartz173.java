    public static JobDetail newJobDetail(Map<String, Object> attrMap)
        throws ClassNotFoundException
    {
        JobDetailImpl jobDetail = new JobDetailImpl();

        int i = 0;
        jobDetail.setName((String) attrMap.get(ITEM_NAMES[i++]));
        jobDetail.setGroup((String) attrMap.get(ITEM_NAMES[i++]));
        jobDetail.setDescription((String) attrMap.get(ITEM_NAMES[i++]));
        Class<?> jobClass = Class.forName((String) attrMap.get(ITEM_NAMES[i++]));
        @SuppressWarnings("unchecked")
        Class<? extends Job> jobClassTyped = (Class<? extends Job>)jobClass;
        jobDetail.setJobClass(jobClassTyped);
        if(attrMap.containsKey(ITEM_NAMES[i])) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)attrMap.get(ITEM_NAMES[i]); 
            jobDetail.setJobDataMap(JobDataMapSupport.newJobDataMap(map));
        }
        i++;
        if(attrMap.containsKey(ITEM_NAMES[i])) {
            jobDetail.setDurability((Boolean) attrMap.get(ITEM_NAMES[i]));
        }
        i++;
        if(attrMap.containsKey(ITEM_NAMES[i])) {
            jobDetail.setRequestsRecovery((Boolean) attrMap.get(ITEM_NAMES[i]));
        }
        i++;
        
        return jobDetail;
    }
