    private ProxyInfo proxyInfo( ArtifactRepository repository )
    {
        ProxyInfo proxyInfo = new ProxyInfo();
        proxyInfo.setHost( repository.getProxy().getHost() );
        proxyInfo.setType( repository.getProxy().getProtocol() );
        proxyInfo.setPort( repository.getProxy().getPort() );
        proxyInfo.setNonProxyHosts( repository.getProxy().getNonProxyHosts() );
        proxyInfo.setUserName( repository.getProxy().getUserName() );
        proxyInfo.setPassword( repository.getProxy().getPassword() );
        return proxyInfo;
    }
