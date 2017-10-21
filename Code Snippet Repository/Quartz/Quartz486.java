    public void validate() throws SchedulerException {
        if (name == null) {
            throw new SchedulerException("Trigger's name cannot be null");
        }

        if (group == null) {
            throw new SchedulerException("Trigger's group cannot be null");
        }

        if (jobName == null) {
            throw new SchedulerException(
                        "Trigger's related Job's name cannot be null");
        }

        if (jobGroup == null) {
            throw new SchedulerException(
                        "Trigger's related Job's group cannot be null");
        }
    }
