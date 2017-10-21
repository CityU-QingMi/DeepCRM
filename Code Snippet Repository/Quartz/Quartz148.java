    public Date scheduleJob(String jobName, String jobGroup,
            String triggerName, String triggerGroup) throws Exception {
        try {
            JobKey jobKey = jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null) {
                throw new IllegalArgumentException("No such job: " + jobKey);
            }
            TriggerKey triggerKey = triggerKey(triggerName, triggerGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                throw new IllegalArgumentException("No such trigger: " + triggerKey);
            }
            return scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
