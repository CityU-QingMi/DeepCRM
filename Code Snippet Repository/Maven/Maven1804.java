    private void invalidMetadata( RepositorySystemSession session, RequestTrace trace, Metadata metadata,
                                  ArtifactRepository repository, Exception exception )
    {
        RepositoryEvent.Builder event = new RepositoryEvent.Builder( session, EventType.METADATA_INVALID );
        event.setTrace( trace );
        event.setMetadata( metadata );
        event.setException( exception );
        event.setRepository( repository );

        repositoryEventDispatcher.dispatch( event.build() );
    }
