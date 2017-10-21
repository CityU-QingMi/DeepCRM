    public Set<String> getCoreArtifactExcludes()
    {
        Set<String> excludes = new LinkedHashSet<>( coreArtifacts );

        for ( ArtifactFilterManagerDelegate delegate : delegates )
        {
            delegate.addCoreExcludes( excludes );
        }

        return excludes;
    }
