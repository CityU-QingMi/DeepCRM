    @Override
    protected void doStart() throws Exception
    {
        if (_sessionIdManager == null)
            throw new IllegalStateException ("No SessionIdManager for Housekeeper");
        
        setIntervalSec(getIntervalSec());
        
        super.doStart();
    }
