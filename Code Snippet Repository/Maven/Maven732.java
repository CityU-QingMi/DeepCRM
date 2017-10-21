    public ReactorContext( MavenExecutionResult result, ProjectIndex projectIndex,
                           ClassLoader originalContextClassLoader, ReactorBuildStatus reactorBuildStatus,
                           SessionScope.Memento sessionScope )
    {
        this.result = result;
        this.projectIndex = projectIndex;
        this.originalContextClassLoader = originalContextClassLoader;
        this.reactorBuildStatus = reactorBuildStatus;
        this.sessionScope = sessionScope;
    }
