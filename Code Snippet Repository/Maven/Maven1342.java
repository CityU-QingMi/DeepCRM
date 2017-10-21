    @Override
    public void transferInitiated( TransferEvent event )
    {
        String action = event.getRequestType() == TransferEvent.RequestType.PUT ? "Uploading" : "Downloading";
        String direction = event.getRequestType() == TransferEvent.RequestType.PUT ? "to" : "from";

        TransferResource resource = event.getResource();
        StringBuilder message = new StringBuilder();
        message.append( action ).append( ' ' ).append( direction ).append( ' ' ).append( resource.getRepositoryId() );
        message.append( ": " );
        message.append( resource.getRepositoryUrl() ).append( resource.getResourceName() );

        out.info( message.toString() );
    }
