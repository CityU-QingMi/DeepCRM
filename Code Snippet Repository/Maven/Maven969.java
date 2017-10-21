    private List<ArtifactRepository> aggregateRepositories( List<ArtifactRepository> requestRepositories,
                                                            List<ArtifactRepository> pomRepositories )
    {
        List<ArtifactRepository> repositories = requestRepositories;

        if ( pomRepositories != null && !pomRepositories.isEmpty() )
        {
            Map<String, ArtifactRepository> repos = new LinkedHashMap<>();

            for ( ArtifactRepository repo : requestRepositories )
            {
                if ( !repos.containsKey( repo.getId() ) )
                {
                    repos.put( repo.getId(), repo );
                }
            }

            for ( ArtifactRepository repo : pomRepositories )
            {
                if ( !repos.containsKey( repo.getId() ) )
                {
                    repos.put( repo.getId(), repo );
                }
            }

            repositories = new ArrayList<>( repos.values() );
        }

        return repositories;
    }
