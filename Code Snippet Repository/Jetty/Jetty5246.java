    public void validate( KeyStore keyStore ) throws CertificateException
    {
        try
        {
            Enumeration<String> aliases = keyStore.aliases();
            
            for ( ; aliases.hasMoreElements(); )
            {
                String alias = aliases.nextElement();
                
                validate(keyStore,alias);
            }
            
        }
        catch ( KeyStoreException kse )
        {
            throw new CertificateException("Unable to retrieve aliases from keystore", kse);
        }
    }
