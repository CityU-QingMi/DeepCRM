    public boolean interrupt(JobKey jobKey) throws UnableToInterruptJobException {

        List<JobExecutionContext> jobs = getCurrentlyExecutingJobs();
        
        JobDetail jobDetail = null;
        Job job = null;
        
        boolean interrupted = false;
        
        for(JobExecutionContext jec : jobs) {
            jobDetail = jec.getJobDetail();
            if (jobKey.equals(jobDetail.getKey())) {
                job = jec.getJobInstance();
                if (job instanceof InterruptableJob) {
                    ((InterruptableJob)job).interrupt();
                    interrupted = true;
                } else {
                    throw new UnableToInterruptJobException(
                            "Job " + jobDetail.getKey() +
                            " can not be interrupted, since it does not implement " +                        
                            InterruptableJob.class.getName());
                }
            }                        
        }
        
        return interrupted;
    }
