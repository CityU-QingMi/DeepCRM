    public ArtifactTransferEvent( String wagon, final int eventType, final int requestType,
                                  ArtifactTransferResource artifact )
    {
        super( wagon );

        setEventType( eventType );

        setRequestType( requestType );

        this.artifact = artifact;
    }
