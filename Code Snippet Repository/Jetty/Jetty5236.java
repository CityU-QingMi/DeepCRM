    @Override
    public String getName()
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        for(Resource r : _resources)
        {
            String name = r.getName();
            if(name!=null)
                return name;
        }
        return null;
    }
