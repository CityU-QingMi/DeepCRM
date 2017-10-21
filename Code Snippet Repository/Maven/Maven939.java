    @Override
    public void addRepository( final Repository repository, boolean replace )
        throws InvalidRepositoryException
    {
        if ( !repositoryIds.add( repository.getId() ) )
        {
            if ( !replace )
            {
                return;
            }

            // Remove any previous repository with this Id
            removeMatchingRepository( repositories, repository.getId() );
            removeMatchingRepository( pomRepositories, repository.getId() );
        }

        List<RemoteRepository> newRepositories =
            Collections.singletonList( ArtifactDescriptorUtils.toRemoteRepository( repository ) );

        if ( ProjectBuildingRequest.RepositoryMerging.REQUEST_DOMINANT.equals( repositoryMerging ) )
        {
            repositories = remoteRepositoryManager.aggregateRepositories( session, repositories, newRepositories,
                                                                          true );
        }
        else
        {
            pomRepositories =
                remoteRepositoryManager.aggregateRepositories( session, pomRepositories, newRepositories, true );
            repositories =
                remoteRepositoryManager.aggregateRepositories( session, pomRepositories, externalRepositories, false );
        }
    }
