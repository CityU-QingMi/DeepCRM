    private List<CoreExtensionEntry> resolveCoreExtensions( RepositorySystemSession repoSession,
                                                            List<RemoteRepository> repositories,
                                                            Set<String> providedArtifacts,
                                                            List<CoreExtension> configuration )
        throws Exception
    {
        List<CoreExtensionEntry> extensions = new ArrayList<>();

        DependencyFilter dependencyFilter = new ExclusionsDependencyFilter( providedArtifacts );

        for ( CoreExtension extension : configuration )
        {
            List<Artifact> artifacts = resolveExtension( extension, repoSession, repositories, dependencyFilter );
            if ( !artifacts.isEmpty() )
            {
                extensions.add( createExtension( extension, artifacts ) );
            }
        }

        return Collections.unmodifiableList( extensions );
    }
