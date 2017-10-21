    public void addJobListener(JobListener jobListener, Matcher<JobKey> matcher) {
        if (jobListener.getName() == null || jobListener.getName().length() == 0) {
            throw new IllegalArgumentException(
                    "JobListener name cannot be empty.");
        }
        
        synchronized (globalJobListeners) {
            globalJobListeners.put(jobListener.getName(), jobListener);
            LinkedList<Matcher<JobKey>> matchersL = new  LinkedList<Matcher<JobKey>>();
            if(matcher != null)
                matchersL.add(matcher);
            else
                matchersL.add(EverythingMatcher.allJobs());
            
            globalJobListenersMatchers.put(jobListener.getName(), matchersL);
        }
    }
