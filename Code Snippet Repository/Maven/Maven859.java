    private void invalidMetadata( RepositorySystemSession session, RequestTrace trace,
                                  org.eclipse.aether.metadata.Metadata metadata, ArtifactRepository repository,
                                  Exception exception )
    {
        RepositoryListener listener = session.getRepositoryListener();
        if ( listener != null )
        {
            RepositoryEvent.Builder event = new RepositoryEvent.Builder( session, EventType.METADATA_INVALID );
            event.setTrace( trace );
            event.setMetadata( metadata );
            event.setException( exception );
            event.setRepository( repository );
            listener.metadataInvalid( event.build() );
        }
    }
