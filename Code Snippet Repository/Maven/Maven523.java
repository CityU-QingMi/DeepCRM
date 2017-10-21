    public ArtifactResolutionResult addVersionRangeViolation( Exception e )
    {
        versionRangeViolations = initList( versionRangeViolations );

        versionRangeViolations.add( e );

        exceptions = initList( exceptions );

        exceptions.add( e );

        return this;
    }
