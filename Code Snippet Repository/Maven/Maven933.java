    public Set<Artifact> getArtifacts()
    {
        if ( artifacts == null )
        {
            if ( artifactFilter == null || resolvedArtifacts == null )
            {
                artifacts = new LinkedHashSet<>();
            }
            else
            {
                artifacts = new LinkedHashSet<>( resolvedArtifacts.size() * 2 );
                for ( Artifact artifact : resolvedArtifacts )
                {
                    if ( artifactFilter.include( artifact ) )
                    {
                        artifacts.add( artifact );
                    }
                }
            }
        }
        return artifacts;
    }
