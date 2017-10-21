    private List<MavenProject> getSchedulableNewProcesses( MavenProject finishedProject )
    {
        List<MavenProject> result = new ArrayList<>();
        // schedule dependent projects, if all of their requirements are met
        for ( MavenProject dependentProject : projectDependencyGraph.getDownstreamProjects( finishedProject, false ) )
        {
            final List<MavenProject> upstreamProjects =
                projectDependencyGraph.getUpstreamProjects( dependentProject, false );
            if ( finishedProjects.containsAll( upstreamProjects ) )
            {
                result.add( dependentProject );
            }
        }
        return result;
    }
