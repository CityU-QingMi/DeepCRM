    @Override
    public MavenExecutionRequest setSelectedProjects( List<String> selectedProjects )
    {
        if ( selectedProjects != null )
        {
            this.selectedProjects = new ArrayList<>( selectedProjects );
        }
        else
        {
            this.selectedProjects = null;
        }

        return this;
    }
