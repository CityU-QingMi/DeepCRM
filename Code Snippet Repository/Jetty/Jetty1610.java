    @Override
    public boolean exists(String id) throws Exception
    {
        // TODO find a better way to do this that does not pull into memory the
        // whole session object
        final AtomicReference<Boolean> reference = new AtomicReference<Boolean>();
        final AtomicReference<Exception> exception = new AtomicReference<Exception>();

        Runnable load = new Runnable()
        {
            public void run ()
            {
                try
                {
                    SessionData sd = load(id);
                    if (sd == null)
                    {
                        reference.set(Boolean.FALSE);
                        return;
                    }

                    if (sd.getExpiry() <= 0)
                        reference.set(Boolean.TRUE); //never expires
                    else
                        reference.set(Boolean.valueOf(sd.getExpiry() > System.currentTimeMillis())); //not expired yet
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
