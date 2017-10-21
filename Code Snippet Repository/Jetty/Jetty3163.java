    public void initRandom ()
    {
        if (_random==null)
        {
            try
            {
                _random=new SecureRandom();
            }
            catch (Exception e)
            {
                LOG.warn("Could not generate SecureRandom for session-id randomness",e);
                _random=new Random();
                _weakRandom=true;
            }
        }
        else
            _random.setSeed(_random.nextLong()^System.currentTimeMillis()^hashCode()^Runtime.getRuntime().freeMemory());
    }
