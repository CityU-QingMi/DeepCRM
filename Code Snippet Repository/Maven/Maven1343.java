    @Override
    public void transferSucceeded( TransferEvent event )
    {
        String action = ( event.getRequestType() == TransferEvent.RequestType.PUT ? "Uploaded" : "Downloaded" );
        String direction = event.getRequestType() == TransferEvent.RequestType.PUT ? "to" : "from";

        TransferResource resource = event.getResource();
        long contentLength = event.getTransferredBytes();
        FileSizeFormat format = new FileSizeFormat( Locale.ENGLISH );

        StringBuilder message = new StringBuilder();
        message.append( action ).append( ' ' ).append( direction ).append( ' ' ).append( resource.getRepositoryId() );
        message.append( ": " );
        message.append( resource.getRepositoryUrl() ).append( resource.getResourceName() );
        message.append( " (" ).append( format.format( contentLength ) );

        long duration = System.currentTimeMillis() - resource.getTransferStartTime();
        if ( duration > 0L )
        {
            double bytesPerSecond = contentLength / ( duration / 1000.0 );
            message.append( " at " ).append( format.format( (long) bytesPerSecond ) ).append( "/s" );
        }

        message.append( ')' );
        out.info( message.toString() );
    }
