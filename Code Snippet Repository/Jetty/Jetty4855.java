    protected Tick formatTick(long now)
    {
        long seconds = now / 1000;

        // Synchronize to protect _tzFormat
        synchronized (this)
        {
            // recheck the tick, to save multiple formats
            if (_tick==null || _tick._seconds!=seconds)
            {
                String s= _tzFormat.format(new Date(now));
                return _tick=new Tick(seconds,s);
            }
            return _tick;
        }
    }
