    @Override
    public MavenExecutionRequest setExcludedProjects( List<String> excludedProjects )
    {
        if ( excludedProjects != null )
        {
            this.excludedProjects = new ArrayList<>( excludedProjects );
        }
        else
        {
            this.excludedProjects = null;
        }

        return this;
    }
