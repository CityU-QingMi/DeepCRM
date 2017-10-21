    public String format(long inDate)
    {
        long seconds = inDate / 1000;

        Tick tick=_tick;
        
        // Is this the cached time
        if (tick==null || seconds!=tick._seconds)
        {
            // It's a cache miss
            Date d = new Date(inDate);
            synchronized (this)
            {
                return _tzFormat.format(d);
            }
        }
        
        return tick._string;
    }
