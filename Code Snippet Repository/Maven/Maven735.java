    public List<MavenProject> getRootSchedulableBuilds()
    {
        List<MavenProject> result = new ArrayList<>();
        for ( ProjectSegment projectBuild : projectBuilds )
        {
            if ( projectDependencyGraph.getUpstreamProjects( projectBuild.getProject(), false ).size() == 0 )
            {
                result.add( projectBuild.getProject() );
            }
        }
        return result;
    }
