    public static void initializeTrigger(MutableTrigger trigger, CompositeData cData) {
        trigger.setDescription((String) cData.get("description"));
        trigger.setCalendarName((String) cData.get("calendarName"));
        if(cData.containsKey("priority")) {
            trigger.setPriority(((Integer)cData.get("priority")).intValue());
        }
        if(cData.containsKey("jobDataMap")) {
            trigger.setJobDataMap(JobDataMapSupport.newJobDataMap((TabularData)cData.get("jobDataMap")));
        }
        Date startTime;
        if(cData.containsKey("startTime")) {
            startTime = (Date) cData.get("startTime");
        } else {
            startTime = new Date();
        }
        trigger.setStartTime(startTime);
        trigger.setEndTime((Date) cData.get("endTime"));
        if(cData.containsKey("misfireInstruction")) {
            trigger.setMisfireInstruction(((Integer)cData.get("misfireInstruction")).intValue());
        }
        trigger.setKey(new TriggerKey((String) cData.get("name"), (String) cData.get("group")));
        trigger.setJobKey(new JobKey((String) cData.get("jobName"), (String) cData.get("jobGroup")));
    }
