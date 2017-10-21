    protected String chooseServerAlias(String keyType, Principal[] issuers, Collection<SNIMatcher> matchers, SSLSession session)
    {
        // Look for the aliases that are suitable for the keytype and issuers
        String[] aliases = _delegate.getServerAliases(keyType,issuers);
        if (aliases==null || aliases.length==0)
            return null;

        // Look for the SNI information.
        String host=null;
        X509 x509=null;
        if (matchers!=null)
        {
            for (SNIMatcher m : matchers)
            {
                if (m instanceof SslContextFactory.AliasSNIMatcher)
                {
                    SslContextFactory.AliasSNIMatcher matcher = (SslContextFactory.AliasSNIMatcher)m;
                    host=matcher.getHost();
                    x509=matcher.getX509();
                    break;
                }
            }
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Matched {} with {} from {}",host,x509,Arrays.asList(aliases));

        // Check if the SNI selected alias is allowable
        if (x509!=null)
        {
            for (String a:aliases)
            {
                if (a.equals(x509.getAlias()))
                {
                    session.putValue(SNI_X509,x509);
                    return a;
                }
            }
            return null;
        }
        return NO_MATCHERS;
    }
