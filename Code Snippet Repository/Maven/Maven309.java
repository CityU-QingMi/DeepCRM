    public void testComparisonByVersion()
    {
        Artifact artifact1 = new DefaultArtifact( groupId, artifactId, VersionRange.createFromVersion( "5.0" ), scope,
                                                  type, classifier, artifactHandler );
        Artifact artifact2 = new DefaultArtifact( groupId, artifactId, VersionRange.createFromVersion( "12.0" ), scope,
                                                  type, classifier, artifactHandler );

        assertTrue( artifact1.compareTo( artifact2 ) < 0 );
        assertTrue( artifact2.compareTo( artifact1 ) > 0 );

        Artifact artifact = new DefaultArtifact( groupId, artifactId, VersionRange.createFromVersion( "5.0" ), scope,
                                                  type, classifier, artifactHandler );
        assertTrue( artifact.compareTo( artifact1 ) == 0 );
        assertTrue( artifact1.compareTo( artifact ) == 0 );
    }
