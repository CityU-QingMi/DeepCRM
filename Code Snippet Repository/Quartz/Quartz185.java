    public static void initializeTrigger(MutableTrigger trigger, Map<String, Object> attrMap) {
        trigger.setDescription((String) attrMap.get("description"));
        trigger.setCalendarName((String) attrMap.get("calendarName"));
        if(attrMap.containsKey("priority")) {
            trigger.setPriority(((Integer)attrMap.get("priority")).intValue());
        }
        if(attrMap.containsKey("jobDataMap")) {
            @SuppressWarnings("unchecked") // cast as expected.
            Map<String, Object> mapTyped = (Map<String, Object>)attrMap.get("jobDataMap");
            trigger.setJobDataMap(JobDataMapSupport.newJobDataMap(mapTyped));
        }
        Date startTime;
        if(attrMap.containsKey("startTime")) {
            startTime = (Date) attrMap.get("startTime");
        } else {
            startTime = new Date();
        }
        trigger.setStartTime(startTime);
        if(attrMap.containsKey("endTime")) {
            trigger.setEndTime((Date) attrMap.get("endTime"));
        }
        if(attrMap.containsKey("misfireInstruction")) {
            trigger.setMisfireInstruction(((Integer)attrMap.get("misfireInstruction")).intValue());
        }
        trigger.setKey(new TriggerKey((String) attrMap.get("name"), (String) attrMap.get("group")));
        trigger.setJobKey(new JobKey((String) attrMap.get("jobName"), (String) attrMap.get("jobGroup")));
    }
