    public synchronized void scan ()
    {
        reportScanStart(++_scanCount);
        scanFiles();
        reportDifferences(_currentScan, _prevScan);
        _prevScan.clear();
        _prevScan.putAll(_currentScan);
        reportScanEnd(_scanCount);
        
        for (Listener l : _listeners)
        {
            try
            {
                if (l instanceof ScanListener)
                    ((ScanListener)l).scan();
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
            catch (Error e)
            {
                LOG.warn(e);
            }
        }
    }
