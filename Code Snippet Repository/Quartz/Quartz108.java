    private boolean matchJobListener(JobListener listener, JobKey key) {
        List<Matcher<JobKey>> matchers = getListenerManager().getJobListenerMatchers(listener.getName());
        if(matchers == null)
            return true;
        for(Matcher<JobKey> matcher: matchers) {
            if(matcher.isMatch(key))
                return true;
        }
        return false;
    }
