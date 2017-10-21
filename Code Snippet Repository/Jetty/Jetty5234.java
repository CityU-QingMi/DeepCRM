    @Override
    public InputStream getInputStream() throws IOException
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        for(Resource r : _resources)
        {
            InputStream is = r.getInputStream();
            if(is!=null)
                return is;
        }
        return null;
    }
