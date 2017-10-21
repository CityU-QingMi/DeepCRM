    private void reportScanStart(int cycle)
    {
        for (Listener listener : _listeners)
        {
            try
            {
                if (listener instanceof ScanCycleListener)
                {
                    ((ScanCycleListener)listener).scanStarted(cycle);
                }
            }
            catch (Exception e)
            {
                LOG.warn(listener + " failed on scan start for cycle " + cycle, e);
            }
        }
    }
