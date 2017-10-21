    public boolean validateDestination(String host, int port)
    {
        String hostPort = host + ":" + port;
        if (!whiteList.isEmpty())
        {
            if (!whiteList.contains(hostPort))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Host {}:{} not whitelisted", host, port);
                return false;
            }
        }
        if (!blackList.isEmpty())
        {
            if (blackList.contains(hostPort))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Host {}:{} blacklisted", host, port);
                return false;
            }
        }
        return true;
    }
