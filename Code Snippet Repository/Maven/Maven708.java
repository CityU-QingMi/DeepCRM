    public static List<MavenProject> getProjects( MavenProject project, MavenSession session, boolean aggregator )
    {
        if ( aggregator )
        {
            return session.getProjects();
        }
        else
        {
            return Collections.singletonList( project );
        }
    }
