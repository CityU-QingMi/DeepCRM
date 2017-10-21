    @Test
    public void testScanDuplicateClasses() throws Exception
    {
        Resource testJar = Resource.newResource(MavenTestingUtils.getTestResourceFile("tinytest.jar"));
        File testClasses = new File(MavenTestingUtils.getTargetDir(), "test-classes");
        AnnotationParser parser = new AnnotationParser();
        Set<Handler> emptySet = Collections.emptySet();
        parser.parse(emptySet, testJar);
        parser.parse(emptySet, Resource.newResource(testClasses));
        List<String> locations = parser.getParsedLocations("org.acme.ClassOne");
        Assert.assertNotNull(locations);
        Assert.assertEquals(2, locations.size());
        Assert.assertTrue(!(locations.get(0).equals(locations.get(1))));
    }
