    private org.apache.maven.settings.Proxy getProxy( ArtifactRepository repository,
                                                      List<org.apache.maven.settings.Proxy> proxies )
    {
        if ( proxies != null && repository.getProtocol() != null )
        {
            for ( org.apache.maven.settings.Proxy proxy : proxies )
            {
                if ( proxy.isActive() && repository.getProtocol().equalsIgnoreCase( proxy.getProtocol() ) )
                {
                    if ( StringUtils.isNotEmpty( proxy.getNonProxyHosts() ) )
                    {
                        ProxyInfo pi = new ProxyInfo();
                        pi.setNonProxyHosts( proxy.getNonProxyHosts() );

                        org.apache.maven.wagon.repository.Repository repo =
                            new org.apache.maven.wagon.repository.Repository( repository.getId(), repository.getUrl() );

                        if ( !ProxyUtils.validateNonProxyHosts( pi, repo.getHost() ) )
                        {
                            return proxy;
                        }
                    }
                    else
                    {
                        return proxy;
                    }
                }
            }
        }

        return null;
    }
