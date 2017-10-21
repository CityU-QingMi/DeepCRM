    private Artifact XcreateArtifact( String groupId, String artifactId, String version, String scope, String type,
                                     String classifier, String inheritedScope )
    {
        VersionRange versionRange = null;
        if ( version != null )
        {
            versionRange = VersionRange.createFromVersion( version );
        }
        return XcreateArtifact( groupId, artifactId, versionRange, type, classifier, scope, inheritedScope );
    }
