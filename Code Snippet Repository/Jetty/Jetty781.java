    protected void fileChanged(String filename) throws Exception
    {
        if (LOG.isDebugEnabled()) 
            LOG.debug("changed {}",filename);
        App app = _appMap.remove(filename);
        if (app != null)
        {
            _deploymentManager.removeApp(app);
        }
        app = ScanningAppProvider.this.createApp(filename);
        if (app != null)
        {
            _appMap.put(filename,app);
            _deploymentManager.addApp(app);
        }
    }
