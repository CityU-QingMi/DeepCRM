    public void testResolveSeparateInstalledClassifiedNonUniqueVersionedArtifacts()
        throws Exception
    {
        VersionRequest requestB = new VersionRequest();
        requestB.addRepository( newTestRepository() );
        Artifact artifactB =
            new DefaultArtifact( "org.apache.maven.its", "dep-mng5324", "classifierB", "jar", "07.20.3-SNAPSHOT" );
        requestB.setArtifact( artifactB );

        VersionResult resultB = versionResolver.resolveVersion( session, requestB );
        assertEquals( "07.20.3-20120809.112920-97", resultB.getVersion() );

        VersionRequest requestA = new VersionRequest();
        requestA.addRepository( newTestRepository() );

        Artifact artifactA =
            new DefaultArtifact( "org.apache.maven.its", "dep-mng5324", "classifierA", "jar", "07.20.3-SNAPSHOT" );
        requestA.setArtifact( artifactA );

        VersionResult resultA = versionResolver.resolveVersion( session, requestA );
        assertEquals( "07.20.3-20120809.112124-88", resultA.getVersion() );
    }
