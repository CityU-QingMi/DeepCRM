    public DefaultModelResolver( RepositorySystemSession session, RequestTrace trace, String context,
                                 ArtifactResolver resolver, VersionRangeResolver versionRangeResolver,
                                 RemoteRepositoryManager remoteRepositoryManager, List<RemoteRepository> repositories )
    {
        this.session = session;
        this.trace = trace;
        this.context = context;
        this.resolver = resolver;
        this.versionRangeResolver = versionRangeResolver;
        this.remoteRepositoryManager = remoteRepositoryManager;
        this.repositories = repositories;
        List<RemoteRepository> externalRepositories = new ArrayList<>();
        externalRepositories.addAll( repositories );
        this.externalRepositories = Collections.unmodifiableList( externalRepositories );

        this.repositoryIds = new HashSet<>();
    }
