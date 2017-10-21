    protected String getRemoteIP(Request baseRequest)
    {
        // Do we have a forwarded header set?
        if (_forwardedHeader!=null && !_forwardedHeader.isEmpty())
        {
            // Yes, then try to get the remote IP from the header
            String remote = _rfc7239?getForwarded(baseRequest):getXForwardedFor(baseRequest);
            if (remote!=null && !remote.isEmpty())
                return remote;
        }
        
        // If no remote IP from a header, determine it directly from the channel
        // Do not use the request methods, as they may have been lied to by the 
        // RequestCustomizer!
        InetSocketAddress inet_addr = baseRequest.getHttpChannel().getRemoteAddress();
        if (inet_addr!=null && inet_addr.getAddress()!=null)
            return inet_addr.getAddress().getHostAddress();
        return null;
    }
