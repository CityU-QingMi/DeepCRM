    protected HostPortHttpField(HttpHeader header, String name, String authority)
    {
        super(header,name,authority);
        try
        {
            _hostPort=new HostPort(authority);
        }
        catch(Exception e)
        {
            throw new BadMessageException(HttpStatus.BAD_REQUEST_400,"Bad HostPort",e);
        }
    }
