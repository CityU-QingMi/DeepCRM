    private String findServerName()
    {
        MetaData.Request metadata = _metaData;
        // Return host from header field
        HttpField host = metadata==null?null:metadata.getFields().getField(HttpHeader.HOST);
        if (host!=null)
        {
            if (!(host instanceof HostPortHttpField) && host.getValue()!=null && !host.getValue().isEmpty())
                host=new HostPortHttpField(host.getValue());    
            if (host instanceof HostPortHttpField)
            {
                HostPortHttpField authority = (HostPortHttpField)host;
                metadata.getURI().setAuthority(authority.getHost(),authority.getPort());
                return authority.getHost();
            }
        }

        // Return host from connection
        String name=getLocalName();
        if (name != null)
            return name;

        // Return the local host
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (java.net.UnknownHostException e)
        {
            LOG.ignore(e);
        }
        return null;
    }
