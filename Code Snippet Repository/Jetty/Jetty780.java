    protected void fileAdded(String filename) throws Exception
    {
        if (LOG.isDebugEnabled()) 
            LOG.debug("added {}",filename);
        App app = ScanningAppProvider.this.createApp(filename);
        if (app != null)
        {
            _appMap.put(filename,app);
            _deploymentManager.addApp(app);
        }
    }
