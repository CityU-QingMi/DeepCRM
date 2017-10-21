    @Override
    public Set<String> getExpired(Set<String> candidates)
    {
        try
        {
            return doGetExpired (candidates);
        }
        finally
        {
            _lastExpiryCheckTime = System.currentTimeMillis();
        }
    }
