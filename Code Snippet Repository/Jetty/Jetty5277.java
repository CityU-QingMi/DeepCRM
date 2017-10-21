    protected PKIXBuilderParameters newPKIXBuilderParameters(KeyStore trustStore, Collection<? extends CRL> crls) throws Exception
    {
        PKIXBuilderParameters pbParams = new PKIXBuilderParameters(trustStore, new X509CertSelector());

        // Set maximum certification path length
        pbParams.setMaxPathLength(_maxCertPathLength);

        // Make sure revocation checking is enabled
        pbParams.setRevocationEnabled(true);
        
        if (_pkixCertPathChecker!=null)
        pbParams.addCertPathChecker(_pkixCertPathChecker);

        if (crls != null && !crls.isEmpty())
        {
            pbParams.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(crls)));
        }

        if (_enableCRLDP)
        {
            // Enable Certificate Revocation List Distribution Points (CRLDP) support
            System.setProperty("com.sun.security.enableCRLDP", "true");
        }

        if (_enableOCSP)
        {
            // Enable On-Line Certificate Status Protocol (OCSP) support
            Security.setProperty("ocsp.enable", "true");

            if (_ocspResponderURL != null)
            {
                // Override location of OCSP Responder
                Security.setProperty("ocsp.responderURL", _ocspResponderURL);
            }
        }
        
        return pbParams;
    }
