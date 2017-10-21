    public boolean getIfNewer( String resourceName, File destination, long timestamp )
        throws TransferFailedException,
               ResourceDoesNotExistException,
               AuthorizationException
    {
        if ( !insideGet )
        {
            addTransfer( "getIfNewer " + resourceName );
        }
        return super.getIfNewer( resourceName, destination, timestamp );
    }
