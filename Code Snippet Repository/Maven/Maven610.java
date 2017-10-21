    @Override
    public MavenExecutionRequest setServers( List<Server> servers )
    {
        if ( servers != null )
        {
            this.servers = new ArrayList<>( servers );
        }
        else
        {
            this.servers = null;
        }

        return this;
    }
