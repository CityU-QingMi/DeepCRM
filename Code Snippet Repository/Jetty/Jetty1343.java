    public void setPathQuery(String path)
    {
        _uri=null;
        _path=null;
        _decodedPath=null;
        _param=null;
        _fragment=null;
        if (path!=null)
            parse(State.PATH,path,0,path.length());
    }
