    protected void doStart() throws Exception
    {
        super.doStart();

        loadUsers();
        if ( isHotReload() && (_configPath != null) )
        {
            this._pathWatcher = new PathWatcher();
            this._pathWatcher.watch(_configPath);
            this._pathWatcher.addListener(this);
            this._pathWatcher.setNotifyExistingOnStart(false);
            this._pathWatcher.start();
        }
       
    }
