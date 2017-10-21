    @Inject
    public BootstrapCoreExtensionManager( Logger log, DefaultPluginDependenciesResolver pluginDependenciesResolver,
                                          DefaultRepositorySystemSessionFactory repositorySystemSessionFactory,
                                          PlexusContainer container )
    {
        this.log = log;
        this.pluginDependenciesResolver = pluginDependenciesResolver;
        this.repositorySystemSessionFactory = repositorySystemSessionFactory;
        this.classWorld = ( (DefaultPlexusContainer) container ).getClassWorld();
        this.parentRealm = container.getContainerRealm();
    }
