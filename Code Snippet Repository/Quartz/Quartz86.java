    public boolean setJobListenerMatchers(String listenerName, List<Matcher<JobKey>> matchers)  {
        if(matchers == null)
            throw new IllegalArgumentException("Non-null value not acceptable.");
        
        synchronized (globalJobListeners) {
            List<Matcher<JobKey>> oldMatchers = globalJobListenersMatchers.get(listenerName);
            if(oldMatchers == null)
                return false;
            globalJobListenersMatchers.put(listenerName, matchers);
            return true;
        }
    }
