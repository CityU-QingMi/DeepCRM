    public static Dependency toDependency( org.apache.maven.artifact.Artifact artifact,
                                           Collection<org.apache.maven.model.Exclusion> exclusions )
    {
        if ( artifact == null )
        {
            return null;
        }

        Artifact result = toArtifact( artifact );

        List<Exclusion> excl = null;
        if ( exclusions != null )
        {
            excl = new ArrayList<>( exclusions.size() );
            for ( org.apache.maven.model.Exclusion exclusion : exclusions )
            {
                excl.add( toExclusion( exclusion ) );
            }
        }

        return new Dependency( result, artifact.getScope(), artifact.isOptional(), excl );
    }
