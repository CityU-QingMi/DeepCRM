    public void transferCompleted( TransferEvent transferEvent )
    {
        ArtifactTransferEvent event = wrap( transferEvent );

        Long transferred;
        synchronized ( transfers )
        {
            transferred = transfers.remove( transferEvent.getResource() );
        }
        if ( transferred != null )
        {
            event.setTransferredBytes( transferred );
        }

        synchronized ( artifacts )
        {
            artifacts.remove( transferEvent.getResource() );
        }

        listener.transferCompleted( event );
    }
