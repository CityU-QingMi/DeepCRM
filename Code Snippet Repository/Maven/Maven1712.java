    protected void mergeDependency_ArtifactId( Dependency target, Dependency source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getArtifactId();
        if ( src != null )
        {
            if ( sourceDominant || target.getArtifactId() == null )
            {
                target.setArtifactId( src );
                target.setLocation( "artifactId", source.getLocation( "artifactId" ) );
            }
        }
    }
