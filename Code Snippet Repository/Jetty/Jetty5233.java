    @Override
    public File getFile() throws IOException
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        for(Resource r : _resources)
        {
            File f = r.getFile();
            if(f!=null)
                return f;
        }
        return null;
    }
