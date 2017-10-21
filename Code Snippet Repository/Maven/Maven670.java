    @Override
    public void metadataResolved( RepositoryEvent event )
    {
        Exception e = event.getException();
        if ( e != null )
        {
            if ( e instanceof MetadataNotFoundException )
            {
                logger.debug( e.getMessage() );
            }
            else if ( logger.isDebugEnabled() )
            {
                logger.warn( e.getMessage(), e );
            }
            else
            {
                logger.warn( e.getMessage() );
            }
        }
    }
