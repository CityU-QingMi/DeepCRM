    public void validate(KeyStore keyStore, Certificate cert) throws CertificateException
    {
        Certificate[] certChain = null;
        
        if (cert != null && cert instanceof X509Certificate)
        {
            ((X509Certificate)cert).checkValidity();
            
            String certAlias = null;
            try
            {
                if (keyStore == null)
                {
                    throw new InvalidParameterException("Keystore cannot be null");
                }

                certAlias = keyStore.getCertificateAlias((X509Certificate)cert);
                if (certAlias == null)
                {
                    certAlias = "JETTY" + String.format("%016X",__aliasCount.incrementAndGet());
                    keyStore.setCertificateEntry(certAlias, cert);
                }
                
                certChain = keyStore.getCertificateChain(certAlias);
                if (certChain == null || certChain.length == 0)
                {
                    throw new IllegalStateException("Unable to retrieve certificate chain");
                }
            }
            catch (KeyStoreException kse)
            {
                LOG.debug(kse);
                throw new CertificateException("Unable to validate certificate" +
                        (certAlias == null ? "":" for alias [" +certAlias + "]") + ": " + kse.getMessage(), kse);
            }
            
            validate(certChain);
        } 
    }
