    protected int getThreadLimit(String ip)
    {
        if (!_includeExcludeSet.isEmpty())
        {
            try
            {
                if (!_includeExcludeSet.test(InetAddress.getByName(ip)))
                {
                    LOG.debug("excluded {}",ip);
                    return 0;
                }
            }
            catch(Exception e)
            {
                LOG.ignore(e);
            }
        }
        return _threadLimit;
    }
