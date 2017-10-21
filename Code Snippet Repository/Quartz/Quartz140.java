    public boolean interrupt(String fireInstanceId) throws UnableToInterruptJobException {
        List<JobExecutionContext> jobs = getCurrentlyExecutingJobs();
        
        Job job = null;
        
        for(JobExecutionContext jec : jobs) {
            if (jec.getFireInstanceId().equals(fireInstanceId)) {
                job = jec.getJobInstance();
                if (job instanceof InterruptableJob) {
                    ((InterruptableJob)job).interrupt();
                    return true;
                } else {
                    throw new UnableToInterruptJobException(
                        "Job " + jec.getJobDetail().getKey() +
                        " can not be interrupted, since it does not implement " +                        
                        InterruptableJob.class.getName());
                }
            }                        
        }
        
        return false;
    }
