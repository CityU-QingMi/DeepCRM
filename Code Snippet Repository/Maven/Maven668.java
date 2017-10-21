    private List<MavenProject> applyFilter( Collection<? extends MavenProject> projects )
    {
        List<MavenProject> filtered = new ArrayList<>( projects.size() );

        for ( MavenProject project : projects )
        {
            if ( whiteList.containsKey( project ) )
            {
                filtered.add( project );
            }
        }

        return filtered;
    }
