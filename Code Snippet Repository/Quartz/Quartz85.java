    public boolean removeJobListenerMatcher(String listenerName, Matcher<JobKey> matcher) {
        if(matcher == null)
            throw new IllegalArgumentException("Non-null value not acceptable.");
        
        synchronized (globalJobListeners) {
            List<Matcher<JobKey>> matchers = globalJobListenersMatchers.get(listenerName);
            if(matchers == null)
                return false;
            return matchers.remove(matcher);
        }
    }
