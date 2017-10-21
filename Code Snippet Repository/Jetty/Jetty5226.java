    @Override
    public URL getURL()
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        for(Resource r : _resources)
        {
            URL url = r.getURL();
            if(url!=null)
                return url;
        }
        return null;
    }
