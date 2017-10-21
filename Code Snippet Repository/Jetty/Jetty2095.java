    @Override
    protected void doStart() throws Exception
    {
        if (_dbSessions == null)
            throw new IllegalStateException("DBCollection not set");
        
        _version_1 = new BasicDBObject(getContextSubfield(__VERSION),1);
        
        ensureIndexes();
        
        super.doStart();
    }
