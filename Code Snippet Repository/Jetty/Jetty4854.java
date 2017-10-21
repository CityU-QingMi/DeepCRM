    public String formatNow(long now)
    {
        long seconds = now / 1000;

        Tick tick=_tick;
        
        // Is this the cached time
        if (tick!=null && tick._seconds==seconds)
            return tick._string;
        return formatTick(now)._string;
    }
