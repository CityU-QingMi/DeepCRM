    public ProjectModelResolver( RepositorySystemSession session, RequestTrace trace, RepositorySystem resolver,
                                 RemoteRepositoryManager remoteRepositoryManager, List<RemoteRepository> repositories,
                                 ProjectBuildingRequest.RepositoryMerging repositoryMerging,
                                 ReactorModelPool modelPool )
    {
        this.session = session;
        this.trace = trace;
        this.resolver = resolver;
        this.remoteRepositoryManager = remoteRepositoryManager;
        this.pomRepositories = new ArrayList<>();
        List<RemoteRepository> externalRepositories = new ArrayList<>();
        externalRepositories.addAll( repositories );
        this.externalRepositories = Collections.unmodifiableList( externalRepositories );
        this.repositories = new ArrayList<>();
        this.repositories.addAll( externalRepositories );
        this.repositoryMerging = repositoryMerging;
        this.repositoryIds = new HashSet<>();
        this.modelPool = modelPool;
    }
