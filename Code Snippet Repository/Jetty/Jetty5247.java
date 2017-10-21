    public String validate(KeyStore keyStore, String keyAlias) throws CertificateException
    {
        String result = null;

        if (keyAlias != null)
        {
            try
            {
                validate(keyStore, keyStore.getCertificate(keyAlias));
            }
            catch (KeyStoreException kse)
            {
                LOG.debug(kse);
                throw new CertificateException("Unable to validate certificate" +
                        " for alias [" + keyAlias + "]: " + kse.getMessage(), kse);
            }
            result = keyAlias;            
        }
        
        return result;
    }
