    private void setupPropertyUserStore(Map<String, ?> options)
    {
        parseConfig(options);

        if (_propertyUserStores.get(_filename) == null)
        {
            PropertyUserStore propertyUserStore = new PropertyUserStore();
            propertyUserStore.setConfig(_filename);

            PropertyUserStore prev = _propertyUserStores.putIfAbsent(_filename, propertyUserStore);
            if (prev == null)
            {
                LOG.debug("setupPropertyUserStore: Starting new PropertyUserStore. PropertiesFile: " + _filename + " refreshInterval: " + _refreshInterval);

                try
                {
                    propertyUserStore.start();
                }
                catch (Exception e)
                {
                    LOG.warn("Exception while starting propertyUserStore: ",e);
                }
            }
        }
    }
