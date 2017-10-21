    public MavenProject getTopLevelProject()
    {
        if ( topLevelProject == null )
        {
            for ( Iterator<MavenProject> i = sortedProjects.iterator(); i.hasNext() && ( topLevelProject == null ); )
            {
                MavenProject project = i.next();
                if ( project.isExecutionRoot() )
                {
                    topLevelProject = project;
                }
            }
        }

        return topLevelProject;
    }
