    @Override
    public void metadataInvalid( RepositoryEvent event )
    {
        Exception exception = event.getException();

        StringBuilder buffer = new StringBuilder( 256 );
        buffer.append( "The metadata " );
        if ( event.getMetadata().getFile() != null )
        {
            buffer.append( event.getMetadata().getFile() );
        }
        else
        {
            buffer.append( event.getMetadata() );
        }

        if ( exception instanceof FileNotFoundException )
        {
            buffer.append( " is inaccessible" );
        }
        else
        {
            buffer.append( " is invalid" );
        }

        if ( exception != null )
        {
            buffer.append( ": " );
            buffer.append( exception.getMessage() );
        }

        if ( logger.isDebugEnabled() )
        {
            logger.warn( buffer.toString(), exception );
        }
        else
        {
            logger.warn( buffer.toString() );
        }
    }
