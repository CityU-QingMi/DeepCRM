    @Test
    public void testGetCoordinate_TestJar()
    {
        MavenLocalRepoFileInitializer repo = new MavenLocalRepoFileInitializer(baseHome);
        String ref = "maven://org.eclipse.jetty/jetty-http/9.3.x/jar/tests";
        Coordinates coords = repo.getCoordinates(URI.create(ref));
        assertThat("Coordinates",coords,notNullValue());

        assertThat("coords.groupId",coords.groupId,is("org.eclipse.jetty"));
        assertThat("coords.artifactId",coords.artifactId,is("jetty-http"));
        assertThat("coords.version",coords.version,is("9.3.x"));
        assertThat("coords.type",coords.type,is("jar"));
        assertThat("coords.classifier",coords.classifier,is("tests"));
        
        assertThat("coords.toCentralURI", coords.toCentralURI().toASCIIString(), 
                is("http://central.maven.org/maven2/org/eclipse/jetty/jetty-http/9.3.x/jetty-http-9.3.x-tests.jar"));
    }
