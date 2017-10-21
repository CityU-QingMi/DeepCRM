    protected KeyManager[] getKeyManagers(KeyStore keyStore) throws Exception
    {
        KeyManager[] managers = null;

        if (keyStore != null)
        {
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(getKeyManagerFactoryAlgorithm());
            keyManagerFactory.init(keyStore, _keyManagerPassword == null ? (_keyStorePassword == null ? null : _keyStorePassword.toString().toCharArray()) : _keyManagerPassword.toString().toCharArray());
            managers = keyManagerFactory.getKeyManagers();

            if (managers != null)
            {
                String alias = getCertAlias();
                if (alias != null)
                {
                    for (int idx = 0; idx < managers.length; idx++)
                    {
                        if (managers[idx] instanceof X509ExtendedKeyManager)
                            managers[idx] = new AliasedX509ExtendedKeyManager((X509ExtendedKeyManager)managers[idx], alias);
                    }
                }

                if (!_certHosts.isEmpty() || !_certWilds.isEmpty())
                {
                    for (int idx = 0; idx < managers.length; idx++)
                    {
                        if (managers[idx] instanceof X509ExtendedKeyManager)
                            managers[idx] = new SniX509ExtendedKeyManager((X509ExtendedKeyManager)managers[idx]);
                    }
                }
            }
        }

        if (LOG.isDebugEnabled())
            LOG.debug("managers={} for {}", managers, this);

        return managers;
    }
