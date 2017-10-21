    @SuppressWarnings( "" )
    private List<ArtifactRepository> normalizeToArtifactRepositories( List<?> repositories,
                                                                      ProjectBuildingRequest request )
        throws ProjectBuildingException
    {
/**/
/**/
/**/
/**/

        if ( repositories != null )
        {
            boolean normalized = false;

            List<ArtifactRepository> repos = new ArrayList<>( repositories.size() );

            for ( Object repository : repositories )
            {
                if ( repository instanceof Repository )
                {
                    try
                    {
                        ArtifactRepository repo = repositorySystem.buildArtifactRepository( (Repository) repository );
                        repositorySystem.injectMirror( request.getRepositorySession(), Arrays.asList( repo ) );
                        repositorySystem.injectProxy( request.getRepositorySession(), Arrays.asList( repo ) );
                        repositorySystem.injectAuthentication( request.getRepositorySession(), Arrays.asList( repo ) );
                        repos.add( repo );
                    }
                    catch ( InvalidRepositoryException e )
                    {
                        throw new ProjectBuildingException( "", "Invalid remote repository " + repository, e );
                    }
                    normalized = true;
                }
                else
                {
                    repos.add( (ArtifactRepository) repository );
                }
            }

            if ( normalized )
            {
                return repos;
            }
        }

        return (List<ArtifactRepository>) repositories;
    }
