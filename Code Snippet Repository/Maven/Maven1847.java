    public void testResolveSeparateInstalledClassifiedNonVersionedArtifacts()
        throws Exception
    {
        VersionRequest requestA = new VersionRequest();
        requestA.addRepository( newTestRepository() );
        String versionA = "07.20.3-20120809.112124-88";
        Artifact artifactA =
            new DefaultArtifact( "org.apache.maven.its", "dep-mng5324", "classifierA", "jar", versionA );
        requestA.setArtifact( artifactA );

        VersionResult resultA = versionResolver.resolveVersion( session, requestA );
        assertEquals( versionA, resultA.getVersion() );

        VersionRequest requestB = new VersionRequest();
        requestB.addRepository( newTestRepository() );
        String versionB = "07.20.3-20120809.112920-97";
        Artifact artifactB =
            new DefaultArtifact( "org.apache.maven.its", "dep-mng5324", "classifierB", "jar", versionB );
        requestB.setArtifact( artifactB );

        VersionResult resultB = versionResolver.resolveVersion( session, requestB );
        assertEquals( versionB, resultB.getVersion() );
    }
