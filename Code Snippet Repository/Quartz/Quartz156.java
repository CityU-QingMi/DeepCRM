    public void scheduleJob(String jobName, String jobGroup,
            Map<String, Object> abstractTriggerInfo) throws Exception {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if(jobDetail == null) {
                throw new IllegalArgumentException("No such job '" + jobKey + "'");
            }
            
            String triggerClassName = (String) abstractTriggerInfo.remove("triggerClass");
            if(triggerClassName == null) {
                throw new IllegalArgumentException("No triggerClass specified");
            }
            Class<?> triggerClass = Class.forName(triggerClassName);
            Trigger trigger = (Trigger) triggerClass.newInstance();
            
            for(Map.Entry<String, Object> entry : abstractTriggerInfo.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if("jobDataMap".equals(key)) {
                    value = new JobDataMap((Map<?, ?>)value);
                }
                invokeSetter(trigger, key, value);
            }
            
            AbstractTrigger<?> at = (AbstractTrigger<?>)trigger;
            at.setKey(new TriggerKey(at.getName(), at.getGroup()));
            
            Date startDate = at.getStartTime();
            if(startDate == null || startDate.before(new Date())) {
                at.setStartTime(new Date());
            }
            
            scheduler.scheduleJob(trigger);
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
