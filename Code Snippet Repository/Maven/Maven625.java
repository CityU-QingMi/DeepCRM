    @Override
    public List<String> getExcludedProjects()
    {
        if ( excludedProjects == null )
        {
            excludedProjects = new ArrayList<>();
        }

        return excludedProjects;
    }
