    @Override
    protected void doStart() throws Exception
    {
        super.doStart();

        // can be null so we switch to previous behaviour using PropertyUserStore
        if (_userStore == null)
        {
            if(LOG.isDebugEnabled())
                LOG.debug("doStart: Starting new PropertyUserStore. PropertiesFile: " + _config + " hotReload: " + hotReload);
            PropertyUserStore propertyUserStore = new PropertyUserStore();
            propertyUserStore.setHotReload(hotReload);
            propertyUserStore.setConfigPath(_config);
            propertyUserStore.start();
            _userStore = propertyUserStore;
            _userStoreAutoCreate = true;
        }
    }
