    public boolean addJobListenerMatcher(String listenerName, Matcher<JobKey> matcher) {
        if(matcher == null)
            throw new IllegalArgumentException("Null value not acceptable.");
        
        synchronized (globalJobListeners) {
            List<Matcher<JobKey>> matchers = globalJobListenersMatchers.get(listenerName);
            if(matchers == null)
                return false;
            matchers.add(matcher);
            return true;
        }
    }
