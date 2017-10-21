    protected RepositorySystemSession initRepoSession()
        throws Exception
    {
        DefaultRepositorySystemSession session = new DefaultRepositorySystemSession();
        session.setArtifactDescriptorPolicy( new SimpleArtifactDescriptorPolicy( true, true ) );
        DependencyTraverser depTraverser = new FatArtifactTraverser();
        session.setDependencyTraverser( depTraverser );

        DependencyManager depManager = new ClassicDependencyManager();
        session.setDependencyManager( depManager );

        DependencySelector depFilter = new AndDependencySelector( new ScopeDependencySelector( "test", "provided" ),
                                                                  new OptionalDependencySelector(),
                                                                  new ExclusionDependencySelector() );
        session.setDependencySelector( depFilter );

        DependencyGraphTransformer transformer =
            new ConflictResolver( new NearestVersionSelector(), new JavaScopeSelector(),
                                  new SimpleOptionalitySelector(), new JavaScopeDeriver() );
        new ChainedDependencyGraphTransformer( transformer, new JavaDependencyContextRefiner() );
        session.setDependencyGraphTransformer( transformer );

        LocalRepository localRepo = new LocalRepository( localRepository().getBasedir() );
        session.setLocalRepositoryManager(
            new SimpleLocalRepositoryManagerFactory().newInstance( session, localRepo ) );

        return session;
    }
