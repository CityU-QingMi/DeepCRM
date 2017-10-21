    public HttpURI(String scheme, String host, int port, String pathQuery)
    {
        _uri=null;
        
        _scheme=scheme;
        _host=host;
        _port=port;

        parse(State.PATH,pathQuery,0,pathQuery.length());
        
    }
