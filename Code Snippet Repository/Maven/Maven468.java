    public ArtifactFilter getArtifactFilter()
    {
        Set<String> excludes = new LinkedHashSet<>( getExcludedArtifacts() );

        for ( ArtifactFilterManagerDelegate delegate : delegates )
        {
            delegate.addExcludes( excludes );
        }

        return new ExclusionSetFilter( excludes );
    }
