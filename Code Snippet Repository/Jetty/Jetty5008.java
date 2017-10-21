    private void reportScanEnd(int cycle)
    {
        for (Listener listener : _listeners)
        {
            try
            {
                if (listener instanceof ScanCycleListener)
                {
                    ((ScanCycleListener)listener).scanEnded(cycle);
                }
            }
            catch (Exception e)
            {
                LOG.warn(listener + " failed on scan end for cycle " + cycle, e);
            }
        }
    }
