    protected KeyStore loadTrustStore(Resource resource) throws Exception
    {
        String type = getTrustStoreType();
        String provider = getTrustStoreProvider();
        String passwd = _trustStorePassword == null ? null : _trustStorePassword.toString();
        if (resource == null || resource.equals(_keyStoreResource))
        {
            resource = _keyStoreResource;
            if (type == null)
                type = _keyStoreType;
            if (provider == null)
                provider = _keyStoreProvider;
            if (passwd == null)
                passwd = _keyStorePassword == null ? null : _keyStorePassword.toString();
        }
        return CertificateUtils.getKeyStore(resource, type, provider, passwd);
    }
