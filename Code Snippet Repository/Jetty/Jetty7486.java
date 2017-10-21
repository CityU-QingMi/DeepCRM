    public HttpsSocketImpl() throws Exception
    {
        @SuppressWarnings("unused")
        HostnameVerifier hostnameVerifier = new HostnameVerifier()
        {
            public boolean verify(String urlHostName, SSLSession session)
            {
                LOG.warn("Warning: URL Host: " + urlHostName + " vs." + session.getPeerHost());
                return true;
            }
        };

        // Install the all-trusting trust manager
        try
        {
            // TODO real trust manager
            this.sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,SslContextFactory.TRUST_ALL_CERTS,new java.security.SecureRandom());
        }
        catch (Exception e)
        {
            throw new IOException("issue ignoring certs");
        }

        sslfactory = sslContext.getSocketFactory();
        
    }
