    @Override
    public MavenExecutionRequest addProxy( Proxy proxy )
    {
        Validate.notNull( proxy, "proxy cannot be null" );

        for ( Proxy p : getProxies() )
        {
            if ( p.getId() != null && p.getId().equals( proxy.getId() ) )
            {
                return this;
            }
        }

        getProxies().add( proxy );

        return this;
    }
