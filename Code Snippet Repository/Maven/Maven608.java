    @Override
    public MavenExecutionRequest setProxies( List<Proxy> proxies )
    {
        if ( proxies != null )
        {
            this.proxies = new ArrayList<>( proxies );
        }
        else
        {
            this.proxies = null;
        }

        return this;
    }
