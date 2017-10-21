    @Override
    public SessionData load( String id )
        throws Exception
    {

        final AtomicReference<SessionData> reference = new AtomicReference<SessionData>();
        final AtomicReference<Exception> exception = new AtomicReference<Exception>();

        //ensure the load runs in the context classloader scope
        _context.run( () -> {
            try
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug( "Loading session {} from hazelcast", id );
                }
                SessionData sd = sessionDataMap.get( getCacheKey( id ) );
                reference.set(sd);
            }
            catch (Exception e)
            {
                exception.set(e);
            }
        } );

        if (exception.get() != null)
        {
            throw exception.get();
        }
        return reference.get();
    }
