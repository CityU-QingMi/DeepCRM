    public void parseRequestTarget(String method,String uri)
    {
        clear();
        _uri=uri;

        if (HttpMethod.CONNECT.is(method))
            _path=uri;
        else
            parse(uri.startsWith("/")?State.PATH:State.START,uri,0,uri.length());
    }
