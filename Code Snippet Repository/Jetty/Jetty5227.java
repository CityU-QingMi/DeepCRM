    @Override
    public long lastModified()
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        for(Resource r : _resources)
        {
            long lm = r.lastModified();
            if (lm!=-1)
                return lm;
        }
        return -1;
    }
