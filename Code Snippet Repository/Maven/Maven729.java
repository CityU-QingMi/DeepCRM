    public Set<MavenProject> getProjects()
    {
        Set<MavenProject> projects = new HashSet<>();

        for ( ProjectSegment s : items )
        {
            projects.add( s.getProject() );
        }
        return projects;
    }
