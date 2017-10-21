    @Inject
    public DefaultClassRealmManager( Logger logger, PlexusContainer container,
                                     List<ClassRealmManagerDelegate> delegates, CoreExportsProvider exports )
    {
        this.logger = logger;
        this.world = ( (MutablePlexusContainer) container ).getClassWorld();
        this.containerRealm = container.getContainerRealm();
        this.delegates = delegates;

        Map<String, ClassLoader> foreignImports = exports.get().getExportedPackages();

        this.mavenApiRealm =
            createRealm( API_REALMID, RealmType.Core, null /* parent */, null /* parentImports */,
                         foreignImports, null /* artifacts */ );

        this.providedArtifacts = exports.get().getExportedArtifacts();
    }
