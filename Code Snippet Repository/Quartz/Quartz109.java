    private boolean matchTriggerListener(TriggerListener listener, TriggerKey key) {
        List<Matcher<TriggerKey>> matchers = getListenerManager().getTriggerListenerMatchers(listener.getName());
        if(matchers == null)
            return true;
        for(Matcher<TriggerKey> matcher: matchers) {
            if(matcher.isMatch(key))
                return true;
        }
        return false;
    }
