    public boolean matchesExclusionPattern(ServletContainerInitializer sci)
    {
        //no exclusion pattern, no SCI is excluded by it
        if (_sciExcludePattern == null)
            return false;
        
        //test if name of class matches the regex
        if (LOG.isDebugEnabled()) 
            LOG.debug("Checking {} against containerInitializerExclusionPattern",sci.getClass().getName());
        return _sciExcludePattern.matcher(sci.getClass().getName()).matches();
    }
