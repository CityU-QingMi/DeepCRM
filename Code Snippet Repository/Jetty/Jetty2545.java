    public void setVirtualHosts( String[] virtualHosts )
    {
        if ( virtualHosts == null )
        {
            _virtualHosts = virtualHosts;
        } 
        else 
        {
            _virtualHosts = new String[virtualHosts.length];
            for ( int i = 0; i < virtualHosts.length; i++ )
                _virtualHosts[i] = normalizeHostname( virtualHosts[i]);
        }
    }
