    public List<MavenProject> getDownstreamProjects( MavenProject project, boolean transitive )
    {
        Validate.notNull( project, "project cannot be null" );

        Set<String> projectIds = new HashSet<>();

        getDownstreamProjects( ProjectSorter.getId( project ), projectIds, transitive );

        return getSortedProjects( projectIds );
    }
