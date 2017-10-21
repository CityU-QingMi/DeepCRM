    public KeyStore getTrustStore()
    {
        if (!isStarted())
            return _setTrustStore;

        synchronized (this)
        {
            return _factory._trustStore;
        }
    }
