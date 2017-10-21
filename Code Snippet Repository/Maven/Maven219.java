    public Artifact createExtensionArtifact( String groupId, String artifactId, String version )
    {
        VersionRange versionRange;
        try
        {
            versionRange = VersionRange.createFromVersionSpec( version );
        }
        catch ( InvalidVersionSpecificationException e )
        {
            // MNG-5368: Log a message instead of returning 'null' silently.
            this.logger.error( String.format(
                "Invalid version specification '%s' creating extension artifact '%s:%s:%s'.",
                version, groupId, artifactId, version, e ) );

            return null;
        }

        return artifactFactory.createExtensionArtifact( groupId, artifactId, versionRange );
    }
