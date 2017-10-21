    private Artifact createArtifact( String groupId, String artifactId, VersionRange versionRange, String type,
                                     String classifier, String scope, String inheritedScope, boolean optional )
    {
        String desiredScope = Artifact.SCOPE_RUNTIME;

        if ( inheritedScope == null )
        {
            desiredScope = scope;
        }
        else if ( Artifact.SCOPE_TEST.equals( scope ) || Artifact.SCOPE_PROVIDED.equals( scope ) )
        {
            return null;
        }
        else if ( Artifact.SCOPE_COMPILE.equals( scope ) && Artifact.SCOPE_COMPILE.equals( inheritedScope ) )
        {
            // added to retain compile artifactScope. Remove if you want compile inherited as runtime
            desiredScope = Artifact.SCOPE_COMPILE;
        }

        if ( Artifact.SCOPE_TEST.equals( inheritedScope ) )
        {
            desiredScope = Artifact.SCOPE_TEST;
        }

        if ( Artifact.SCOPE_PROVIDED.equals( inheritedScope ) )
        {
            desiredScope = Artifact.SCOPE_PROVIDED;
        }

        if ( Artifact.SCOPE_SYSTEM.equals( scope ) )
        {
            // system scopes come through unchanged...
            desiredScope = Artifact.SCOPE_SYSTEM;
        }

        ArtifactHandler handler = artifactHandlerManager.getArtifactHandler( type );

        return new DefaultArtifact( groupId, artifactId, versionRange, desiredScope, type, classifier, handler,
                                    optional );
    }
