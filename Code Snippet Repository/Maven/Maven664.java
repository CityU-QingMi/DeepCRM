    public List<MavenProject> getUpstreamProjects( MavenProject project, boolean transitive )
    {
        Validate.notNull( project, "project cannot be null" );

        Set<String> projectIds = new HashSet<>();

        getUpstreamProjects( ProjectSorter.getId( project ), projectIds, transitive );

        return getSortedProjects( projectIds );
    }
