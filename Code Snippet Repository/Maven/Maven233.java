    public void transferError( TransferEvent transferEvent )
    {
        synchronized ( transfers )
        {
            transfers.remove( transferEvent.getResource() );
        }
        synchronized ( artifacts )
        {
            artifacts.remove( transferEvent.getResource() );
        }
    }
