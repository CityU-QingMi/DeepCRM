    public boolean validateDestination(String host, int port)
    {
        String hostPort = host + ":" + port;
        if (!_whiteList.isEmpty())
        {
            if (!_whiteList.contains(hostPort))
            {
                if (_log.isDebugEnabled())
                    _log.debug("Host {}:{} not whitelisted", host, port);
                return false;
            }
        }
        if (!_blackList.isEmpty())
        {
            if (_blackList.contains(hostPort))
            {
                if (_log.isDebugEnabled())
                    _log.debug("Host {}:{} blacklisted", host, port);
                return false;
            }
        }
        return true;
    }
