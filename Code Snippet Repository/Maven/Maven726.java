    public Map<MavenProject, ProjectSegment> selectSegment( TaskSegment taskSegment )
    {
        Map<MavenProject, ProjectSegment> result = new HashMap<>();
        for ( ProjectSegment projectBuild : items )
        {
            if ( taskSegment == projectBuild.getTaskSegment() )
            { // NOTE: There's no notion of taskSegment equality.
                result.put( projectBuild.getProject(), projectBuild );
            }
        }
        return result;
    }
