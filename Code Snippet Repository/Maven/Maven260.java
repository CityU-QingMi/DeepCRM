    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( 256 );
        sb.append( "[scope=" ).append( scope.getScope() );
        if ( classpath != null )
        {
            for ( ArtifactMetadata md : classpath )
            {
                sb.append( ": " ).append( md.toString() ).append( '{' ).append( md.getArtifactUri() ).append( '}' );
            }
        }
        sb.append( ']' );
        return sb.toString();
    }
