    @Override
    public void artifactDescriptorInvalid( RepositoryEvent event )
    {
        StringBuilder buffer = new StringBuilder( 256 );
        buffer.append( "The POM for " );
        buffer.append( event.getArtifact() );
        buffer.append( " is invalid, transitive dependencies (if any) will not be available" );

        if ( logger.isDebugEnabled() )
        {
            logger.warn( buffer + ": " + event.getException().getMessage() );
        }
        else
        {
            logger.warn( buffer + ", enable debug logging for more details" );
        }
    }
