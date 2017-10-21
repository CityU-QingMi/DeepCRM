    public boolean addTriggerListenerMatcher(String listenerName, Matcher<TriggerKey> matcher) {
        if(matcher == null)
            throw new IllegalArgumentException("Non-null value not acceptable.");
        
        synchronized (globalTriggerListeners) {
            List<Matcher<TriggerKey>> matchers = globalTriggerListenersMatchers.get(listenerName);
            if(matchers == null)
                return false;
            matchers.add(matcher);
            return true;
        }
    }
