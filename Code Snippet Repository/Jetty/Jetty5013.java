    public synchronized void scanFiles ()
    {
        if (_scanDirs==null)
            return;
        
        _currentScan.clear();
        Iterator<File> itor = _scanDirs.iterator();
        while (itor.hasNext())
        {
            File dir = itor.next();
            
            if ((dir != null) && (dir.exists()))
                try
                {
                    scanFile(dir.getCanonicalFile(), _currentScan,0);
                }
                catch (IOException e)
                {
                    LOG.warn("Error scanning files.", e);
                }
        }
    }
