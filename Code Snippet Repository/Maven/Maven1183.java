    public void testBuildDirectoriesUsePlatformSpecificFileSeparator()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "platform-file-separator" );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/directory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/outputDirectory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/testOutputDirectory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/sourceDirectory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/testSourceDirectory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/resources[1]/directory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/testResources[1]/directory" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "build/filters[1]" ) );
        assertPathWithNormalizedFileSeparators( pom.getValue( "reporting/outputDirectory" ) );
    }
