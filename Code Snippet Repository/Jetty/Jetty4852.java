    public String format(Date inDate)
    {
        long seconds = inDate.getTime() / 1000;

        Tick tick=_tick;
        
        // Is this the cached time
        if (tick==null || seconds!=tick._seconds)
        {
            // It's a cache miss
            synchronized (this)
            {
                return _tzFormat.format(inDate);
            }
        }
        
        return tick._string;
    }
