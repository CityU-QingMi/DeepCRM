    public boolean setTriggerListenerMatchers(String listenerName, List<Matcher<TriggerKey>> matchers)  {
        if(matchers == null)
            throw new IllegalArgumentException("Non-null value not acceptable.");
        
        synchronized (globalTriggerListeners) {
            List<Matcher<TriggerKey>> oldMatchers = globalTriggerListenersMatchers.get(listenerName);
            if(oldMatchers == null)
                return false;
            globalTriggerListenersMatchers.put(listenerName, matchers);
            return true;
        }
    }
