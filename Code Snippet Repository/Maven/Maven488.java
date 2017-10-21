    private static Proxy toProxy( org.apache.maven.repository.Proxy proxy )
    {
        Proxy result = null;
        if ( proxy != null )
        {
            AuthenticationBuilder authBuilder = new AuthenticationBuilder();
            authBuilder.addUsername( proxy.getUserName() ).addPassword( proxy.getPassword() );
            result = new Proxy( proxy.getProtocol(), proxy.getHost(), proxy.getPort(), authBuilder.build() );
        }
        return result;
    }
