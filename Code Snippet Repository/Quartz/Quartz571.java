    public boolean removeJobs(List<JobKey> jobKeys)
            throws JobPersistenceException {
        boolean allFound = true;

        synchronized (lock) {
            for(JobKey key: jobKeys)
                allFound = removeJob(key) && allFound;
        }

        return allFound;
    }
