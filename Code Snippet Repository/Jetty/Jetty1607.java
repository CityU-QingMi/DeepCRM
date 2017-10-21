    @Override
    public SessionData load(String id) throws Exception
    {  
        final AtomicReference<SessionData> reference = new AtomicReference<SessionData>();
        final AtomicReference<Exception> exception = new AtomicReference<Exception>();
        
        Runnable load = new Runnable()
        {
            public void run ()
            {
                try
                {

                    if (LOG.isDebugEnabled())
                        LOG.debug("Loading session {} from infinispan", id);
     
                    SessionData sd = (SessionData)_cache.get(getCacheKey(id));
                    reference.set(sd);
                }
                catch (Exception e)
                {
                    exception.set(e);
                }
            }
        };
        
        //ensure the load runs in the context classloader scope
        _context.run(load);
        
        if (exception.get() != null)
            throw exception.get();
        
        return reference.get();
    }
