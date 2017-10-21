    @Override
    public void addRepository( final Repository repository, boolean replace )
        throws InvalidRepositoryException
    {
        if ( session.isIgnoreArtifactDescriptorRepositories() )
        {
            return;
        }

        if ( !repositoryIds.add( repository.getId() ) )
        {
            if ( !replace )
            {
                return;
            }

            removeMatchingRepository( repositories, repository.getId() );
        }

        List<RemoteRepository> newRepositories =
            Collections.singletonList( ArtifactDescriptorUtils.toRemoteRepository( repository ) );

        this.repositories =
            remoteRepositoryManager.aggregateRepositories( session, repositories, newRepositories, true );
    }
