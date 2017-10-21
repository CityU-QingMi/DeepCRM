    @Test
    public void testGetCoordinate_Zip()
    {
        MavenLocalRepoFileInitializer repo = new MavenLocalRepoFileInitializer(baseHome);
        String ref = "maven://org.eclipse.jetty/jetty-distribution/9.3.x/zip";
        Coordinates coords = repo.getCoordinates(URI.create(ref));
        assertThat("Coordinates",coords,notNullValue());

        assertThat("coords.groupId",coords.groupId,is("org.eclipse.jetty"));
        assertThat("coords.artifactId",coords.artifactId,is("jetty-distribution"));
        assertThat("coords.version",coords.version,is("9.3.x"));
        assertThat("coords.type",coords.type,is("zip"));
        assertThat("coords.classifier",coords.classifier,nullValue());
        
        assertThat("coords.toCentralURI", coords.toCentralURI().toASCIIString(), 
                is("http://central.maven.org/maven2/org/eclipse/jetty/jetty-distribution/9.3.x/jetty-distribution-9.3.x.zip"));
    }
