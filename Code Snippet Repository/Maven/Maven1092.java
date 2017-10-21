    public List<MavenProject> getDownstreamProjects( MavenProject project, boolean transitive )
    {
        if ( transitive )
        {
            throw new RuntimeException( "Not implemented yet" );
        }
        List<MavenProject> result = new ArrayList<>();
        for ( Dependency dependency : getDependencies() )
        {
            dependency.addIfDownstream( project, result );
        }
        return result;
    }
