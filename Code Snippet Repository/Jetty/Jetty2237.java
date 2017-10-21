    @Override
    protected void doStop() throws Exception
    {
        _scanner.removeListener(_listener);
        _scanner.stop();
        
        if (_deploymentManager.isRunning())
        {
            for (App app: _deployed.values())
            _deploymentManager.removeApp(app);
        }
        _deployed.clear();
        
        for (Layer layer : _webapps.values())
            layer.release();
        _webapps.clear();
        for (Layer layer : _templates.values())
            layer.release();
        _templates.clear();
        if (_node!=null)
            _node.release();
        for (Layer layer : _instances.values())
            layer.release();
        _instances.clear();
        
        super.doStop();
    }
