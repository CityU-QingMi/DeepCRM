    public void injectProxy( List<ArtifactRepository> repositories, List<org.apache.maven.settings.Proxy> proxies )
    {
        if ( repositories != null )
        {
            for ( ArtifactRepository repository : repositories )
            {
                org.apache.maven.settings.Proxy proxy = getProxy( repository, proxies );

                if ( proxy != null )
                {
                    SettingsDecryptionRequest request = new DefaultSettingsDecryptionRequest( proxy );
                    SettingsDecryptionResult result = settingsDecrypter.decrypt( request );
                    proxy = result.getProxy();

                    if ( logger.isDebugEnabled() )
                    {
                        for ( SettingsProblem problem : result.getProblems() )
                        {
                            logger.debug( problem.getMessage(), problem.getException() );
                        }
                    }

                    Proxy p = new Proxy();
                    p.setHost( proxy.getHost() );
                    p.setProtocol( proxy.getProtocol() );
                    p.setPort( proxy.getPort() );
                    p.setNonProxyHosts( proxy.getNonProxyHosts() );
                    p.setUserName( proxy.getUsername() );
                    p.setPassword( proxy.getPassword() );

                    repository.setProxy( p );
                }
                else
                {
                    repository.setProxy( null );
                }
            }
        }
    }
