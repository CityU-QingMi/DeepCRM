    public ProjectBuildList getByTaskSegment( TaskSegment taskSegment )
    {
        List<ProjectSegment> currentSegment = new ArrayList<>();
        for ( ProjectSegment projectBuild : items )
        {
            if ( taskSegment == projectBuild.getTaskSegment() )
            { // NOTE: There's no notion of taskSegment equality.
                currentSegment.add( projectBuild );
            }
        }
        return new ProjectBuildList( currentSegment );
    }
