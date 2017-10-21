    public DependencyResolutionResult resolve( DependencyResolutionRequest request )
        throws DependencyResolutionException
    {
        return new DependencyResolutionResult()
        {

            public List<Dependency> getUnresolvedDependencies()
            {
                return Collections.emptyList();
            }

            public List<Dependency> getResolvedDependencies()
            {
                return Collections.emptyList();
            }

            public List<Exception> getResolutionErrors( Dependency dependency )
            {
                return Collections.emptyList();
            }

            public DependencyNode getDependencyGraph()
            {
                return new DefaultDependencyNode( (Dependency) null );
            }

            public List<Dependency> getDependencies()
            {
                return Collections.emptyList();
            }

            public List<Exception> getCollectionErrors()
            {
                return Collections.emptyList();
            }
        };
    }
