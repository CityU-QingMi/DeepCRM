    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( 128 );
        sb.append( "MavenProject: " );
        sb.append( getGroupId() );
        sb.append( ':' );
        sb.append( getArtifactId() );
        sb.append( ':' );
        sb.append( getVersion() );
        sb.append( " @ " );

        try
        {
            sb.append( getFile().getPath() );
        }
        catch ( NullPointerException e )
        {
            // don't log it.
        }

        return sb.toString();
    }
