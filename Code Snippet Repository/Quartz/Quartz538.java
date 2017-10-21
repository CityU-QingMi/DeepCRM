    public void addJobChainLink(JobKey firstJob, JobKey secondJob) {

        if(firstJob == null || secondJob == null) {
            throw new IllegalArgumentException("Key cannot be null!");
        }

        if(firstJob.getName() == null || secondJob.getName() == null) {
            throw new IllegalArgumentException("Key cannot have a null name!");
        }

        chainLinks.put(firstJob, secondJob);
    }
