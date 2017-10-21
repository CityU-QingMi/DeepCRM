    public void addInternalJobListener(JobListener jobListener) {
        if (jobListener.getName() == null
                || jobListener.getName().length() == 0) {
            throw new IllegalArgumentException(
                    "JobListener name cannot be empty.");
        }
        
        synchronized (internalJobListeners) {
            internalJobListeners.put(jobListener.getName(), jobListener);
        }
    }
