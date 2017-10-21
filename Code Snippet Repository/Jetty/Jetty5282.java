    public KeyStore getKeyStore()
    {
        if (!isStarted())
            return _setKeyStore;

        synchronized (this)
        {
            return _factory._keyStore;
        }
    }
