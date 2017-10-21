    public void run()
    {
        // Initialize repeat flag
        boolean repeat = false;
        boolean scanNow, logNow;

        // Set next scan time and log time
        long nextScanTime = System.currentTimeMillis();
        long nextLogTime = nextScanTime + _logInterval;
        
        while (!_done)
        {
            long currTime = System.currentTimeMillis();
            scanNow = (currTime > nextScanTime);
            logNow = (_logInterval > 0 && currTime > nextLogTime);
            if (repeat || scanNow || logNow)
            {
                repeat = collectThreadInfo();
                logThreadInfo(logNow);

                if (scanNow)
                {
                    nextScanTime = System.currentTimeMillis() + _scanInterval;
                }
                if (logNow)
                {
                    nextLogTime = System.currentTimeMillis() + _logInterval;
                }
            }

            // Sleep only if not going to repeat scanning immediately
            if (!repeat)
            {
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException ex)
                {
                    LOG.ignore(ex);
                }
            }
        }
        
    }
