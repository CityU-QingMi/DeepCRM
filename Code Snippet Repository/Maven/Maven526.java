    public ArtifactResolutionResult addCircularDependencyException( CyclicDependencyException e )
    {
        circularDependencyExceptions = initList( circularDependencyExceptions );

        circularDependencyExceptions.add( e );

        exceptions = initList( exceptions );

        exceptions.add( e );

        return this;
    }
