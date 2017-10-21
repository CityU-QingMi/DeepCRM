    public List<MavenProject> getUpstreamProjects( MavenProject project, boolean transitive )
    {
/**/
/**/
/**/
/**/
        List<MavenProject> result = new ArrayList<>();
        final List<Dependency> dependencies = getDependencies();
        for ( Dependency dependency : dependencies )
        {
            dependency.addIfUpstreamOf( project, result );
        }
        return result;
    }
