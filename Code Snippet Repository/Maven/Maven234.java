    public void transferProgress( TransferEvent transferEvent, byte[] buffer, int length )
    {
        Long transferred;
        synchronized ( transfers )
        {
            transferred = transfers.get( transferEvent.getResource() );
            if ( transferred == null )
            {
                transferred = (long) length;
            }
            else
            {
                transferred = transferred + length;
            }
            transfers.put( transferEvent.getResource(), transferred );
        }

        ArtifactTransferEvent event = wrap( transferEvent );
        event.setDataBuffer( buffer );
        event.setDataOffset( 0 );
        event.setDataLength( length );
        event.setTransferredBytes( transferred );

        listener.transferProgress( event );
    }
