    public void setJobGroup(String jobGroup) {
        if (jobGroup != null && jobGroup.trim().length() == 0) {
            throw new IllegalArgumentException(
                    "Group name cannot be null or empty.");
        }

        if(jobGroup == null) {
            jobGroup = Scheduler.DEFAULT_GROUP;
        }

        this.jobGroup = jobGroup;
    }
