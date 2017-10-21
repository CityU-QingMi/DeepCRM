    @Override
    public List<Server> getServers()
    {
        if ( servers == null )
        {
            servers = new ArrayList<>();
        }

        return servers;
    }
