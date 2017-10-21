    protected Remote getRemote(Request baseRequest)
    {
        Remote remote = (Remote)baseRequest.getAttribute(REMOTE);
        if (remote!=null)
            return remote;
        
        String ip=getRemoteIP(baseRequest);
        LOG.debug("ip={}",ip);
        if (ip==null)
            return null;
        
        int limit = getThreadLimit(ip);
        if (limit<=0)
            return null;

        remote = _remotes.get(ip);
        if (remote==null)
        {
            Remote r = new Remote(ip,limit);
            remote = _remotes.putIfAbsent(ip,r);
            if (remote==null)
                remote = r;
        }
        
        baseRequest.setAttribute(REMOTE,remote);

        return remote;
    }
