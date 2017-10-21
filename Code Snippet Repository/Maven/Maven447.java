    private ArtifactSpec createArtifactSpec( String id, String version, String scope, String inheritedScope,
                                             boolean optional )
        throws InvalidVersionSpecificationException
    {
        VersionRange versionRange = VersionRange.createFromVersionSpec( version );
        Artifact artifact =
            artifactFactory.createDependencyArtifact( GROUP_ID, id, versionRange, "jar", null, scope, inheritedScope,
                                                      optional );
        ArtifactSpec spec = null;
        if ( artifact != null )
        {
            spec = new ArtifactSpec();
            spec.artifact = artifact;
            source.addArtifact( spec );
        }
        return spec;
    }
