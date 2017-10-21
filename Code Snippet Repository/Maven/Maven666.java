    private List<MavenProject> getSortedProjects( Set<String> projectIds )
    {
        List<MavenProject> result = new ArrayList<>( projectIds.size() );

        for ( MavenProject mavenProject : sorter.getSortedProjects() )
        {
            if ( projectIds.contains( ProjectSorter.getId( mavenProject ) ) )
            {
                result.add( mavenProject );
            }
        }

        return result;
    }
