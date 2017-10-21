    private void invalidDescriptor( RepositorySystemSession session, RequestTrace trace, Artifact artifact,
                                    Exception exception )
    {
        RepositoryEvent.Builder event = new RepositoryEvent.Builder( session, EventType.ARTIFACT_DESCRIPTOR_INVALID );
        event.setTrace( trace );
        event.setArtifact( artifact );
        event.setException( exception );

        repositoryEventDispatcher.dispatch( event.build() );
    }
