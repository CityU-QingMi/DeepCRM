    private static ArtifactFilter createDependencyFilter( Dependency dependency, ArtifactFilter inheritedFilter )
    {
        ArtifactFilter effectiveFilter = inheritedFilter;

        if ( !dependency.getExclusions().isEmpty() )
        {
            List<String> exclusions = new ArrayList<>();

            for ( Exclusion e : dependency.getExclusions() )
            {
                exclusions.add( e.getGroupId() + ':' + e.getArtifactId() );
            }

            effectiveFilter = new ExcludesArtifactFilter( exclusions );

            if ( inheritedFilter != null )
            {
                effectiveFilter = new AndArtifactFilter( Arrays.asList( inheritedFilter, effectiveFilter ) );
            }
        }

        return effectiveFilter;
    }
