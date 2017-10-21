    @Override
    public MavenExecutionRequest addServer( Server server )
    {
        Validate.notNull( server, "server cannot be null" );

        for ( Server p : getServers() )
        {
            if ( p.getId() != null && p.getId().equals( server.getId() ) )
            {
                return this;
            }
        }

        getServers().add( server );

        return this;
    }
