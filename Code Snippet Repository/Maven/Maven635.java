    public void setProjects( List<MavenProject> projects )
    {
        if ( !projects.isEmpty() )
        {
            this.currentProject = projects.get( 0 );
            this.topLevelProject = currentProject;
            for ( MavenProject project : projects )
            {
                if ( project.isExecutionRoot() )
                {
                    topLevelProject = project;
                    break;
                }
            }
        }
        else
        {
            this.currentProject = null;
            this.topLevelProject = null;
        }
        this.projects = projects;
    }
