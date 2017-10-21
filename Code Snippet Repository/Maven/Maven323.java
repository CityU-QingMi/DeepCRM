    public void get( String resourceName, File destination )
        throws TransferFailedException,
               ResourceDoesNotExistException,
               AuthorizationException
    {
        addTransfer( "get " + resourceName );

        insideGet = true;

        super.get( resourceName, destination );

        insideGet = false;
    }
