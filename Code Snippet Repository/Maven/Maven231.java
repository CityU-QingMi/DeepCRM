    public static TransferListener newAdapter( ArtifactTransferListener listener )
    {
        if ( listener == null )
        {
            return null;
        }
        else
        {
            return new TransferListenerAdapter( listener );
        }
    }
