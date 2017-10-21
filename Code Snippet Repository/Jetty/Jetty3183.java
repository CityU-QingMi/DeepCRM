    public void setIntervalSec (long sec) throws Exception
    {
        if (isStarted() || isStarting())
        {
            if (sec <= 0)
            {
                _intervalMs = 0L;
                LOG.info("Scavenging disabled");
                stopScavenging();
            }
            else
            {
                if (sec < 10)
                    LOG.warn("Short interval of {}sec for session scavenging.", sec);
                
                _intervalMs=sec*1000L;

                //add a bit of variability into the scavenge time so that not all
                //nodes with the same scavenge interval sync up
                long tenPercent = _intervalMs/10;
                if ((System.currentTimeMillis()%2) == 0)
                    _intervalMs += tenPercent;
                
                if (isStarting() || isStarted())
                {
                    findScheduler();
                    startScavenging();
                }
            }
        }
        else
        {
            _intervalMs=sec*1000L;
        }

    }
