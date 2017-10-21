    @Override
    public boolean exists(String id) throws Exception
    {
        //try the object store first
        Session s = doGet(id);
        if (s != null)
        {
            try (Lock lock = s.lock())
            {
                //wait for the lock and check the validity of the session
                return s.isValid();
            }
        }
        
        //not there, so find out if session data exists for it
        return _sessionDataStore.exists (id);
    }
