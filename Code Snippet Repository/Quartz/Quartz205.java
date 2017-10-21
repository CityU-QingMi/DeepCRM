    @Override
    public String toString() {
        return "JobExecutionContext:" + " trigger: '"
                + getTrigger().getKey() + " job: "
                + getJobDetail().getKey() + " fireTime: '" + getFireTime()
                + " scheduledFireTime: " + getScheduledFireTime()
                + " previousFireTime: '" + getPreviousFireTime()
                + " nextFireTime: " + getNextFireTime() + " isRecovering: "
                + isRecovering() + " refireCount: " + getRefireCount();
    }
