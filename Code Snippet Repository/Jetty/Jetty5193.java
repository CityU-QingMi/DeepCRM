    @Override
    protected synchronized void newConnection()
        throws IOException
    {
        super.newConnection();
        
        _entry=null;
        _file=null;
        _jarFile=null;
        _list=null;
        
        int sep = _urlString.lastIndexOf("!/");
        _jarUrl=_urlString.substring(0,sep+2);
        _path=URIUtil.decodePath(_urlString.substring(sep+2));
        if (_path.length()==0)
            _path=null;   
        _jarFile=_jarConnection.getJarFile();
        _file=new File(_jarFile.getName());
    }
