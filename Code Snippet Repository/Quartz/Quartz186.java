    public JobRunShell createJobRunShell(TriggerFiredBundle bundle)
            throws SchedulerException {
        ExecuteInJTATransaction jtaAnnotation = ClassUtils.getAnnotation(bundle.getJobDetail().getJobClass(), ExecuteInJTATransaction.class);
        if(jtaAnnotation == null)
            return new JobRunShell(scheduler, bundle);
        else {
            int timeout = jtaAnnotation.timeout();
            if (timeout >= 0) {
                return new JTAJobRunShell(scheduler, bundle, timeout);
            } else {
                return new JTAJobRunShell(scheduler, bundle);
            }
        }
    }
