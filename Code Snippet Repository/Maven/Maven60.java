    private void checkVersionParsing( String version, int major, int minor, int incremental, int buildnumber,
                                      String qualifier )
    {
        ArtifactVersion artifactVersion = newArtifactVersion( version );
        String parsed =
            "'" + version + "' parsed as ('" + artifactVersion.getMajorVersion() + "', '"
                + artifactVersion.getMinorVersion() + "', '" + artifactVersion.getIncrementalVersion() + "', '"
                + artifactVersion.getBuildNumber() + "', '" + artifactVersion.getQualifier() + "'), ";
        assertEquals( parsed + "check major version", major, artifactVersion.getMajorVersion() );
        assertEquals( parsed + "check minor version", minor, artifactVersion.getMinorVersion() );
        assertEquals( parsed + "check incremental version", incremental, artifactVersion.getIncrementalVersion() );
        assertEquals( parsed + "check build number", buildnumber, artifactVersion.getBuildNumber() );
        assertEquals( parsed + "check qualifier", qualifier, artifactVersion.getQualifier() );
        assertEquals( "check " + version + " string value", version, artifactVersion.toString() );
    }
