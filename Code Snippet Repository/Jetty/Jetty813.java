    public void waitForDirectoryScan()
    {
        int scan=_scans.get()+(2*_providers);
        do
        {
            try
            {
                Thread.sleep(200);
            }
            catch(InterruptedException e)
            {
                LOG.warn(e);
            }
        }
        while(_scans.get()<scan);
    }
