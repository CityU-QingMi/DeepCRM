    private ArtifactTransferEvent wrap( TransferEvent event )
    {
        if ( event == null )
        {
            return null;
        }
        else
        {
            String wagon = event.getWagon().getClass().getName();

            ArtifactTransferResource artifact = wrap( event.getWagon().getRepository(), event.getResource() );

            ArtifactTransferEvent evt;
            if ( event.getException() != null )
            {
                evt = new ArtifactTransferEvent( wagon, event.getException(), event.getRequestType(), artifact );
            }
            else
            {
                evt = new ArtifactTransferEvent( wagon, event.getEventType(), event.getRequestType(), artifact );
            }

            evt.setLocalFile( event.getLocalFile() );

            return evt;
        }
    }
