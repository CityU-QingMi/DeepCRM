    public void scheduleBasicJob(Map<String, Object> jobDetailInfo,
            Map<String, Object> triggerInfo) throws Exception {
        try {
            JobDetail jobDetail = JobDetailSupport.newJobDetail(jobDetailInfo);
            OperableTrigger trigger = TriggerSupport.newTrigger(triggerInfo);
            scheduler.deleteJob(jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ParseException pe) {
            throw pe;
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
