    public String toString()
    {
        StringBuilder sb = new StringBuilder( 256 );

        sb.append( "      id: " ).append( getId() ).append( '\n' );
        sb.append( "      url: " ).append( getUrl() ).append( '\n' );
        sb.append( "   layout: " ).append( layout != null ? layout : "none" ).append( '\n' );

        if ( proxy != null )
        {
            sb.append( "    proxy: " ).append( proxy.getHost() ).append( ':' ).append( proxy.getPort() ).append( '\n' );
        }

        if ( snapshots != null )
        {
            sb.append( "snapshots: [enabled => " ).append( snapshots.isEnabled() );
            sb.append( ", update => " ).append( snapshots.getUpdatePolicy() ).append( "]\n" );
        }

        if ( releases != null )
        {
            sb.append( " releases: [enabled => " ).append( releases.isEnabled() );
            sb.append( ", update => " ).append( releases.getUpdatePolicy() ).append( "]\n" );
        }

        return sb.toString();
    }
