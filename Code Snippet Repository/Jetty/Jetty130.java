    @Test
    public void testScanDuplicateClassesInJars() throws Exception
    {
        Resource testJar = Resource.newResource(MavenTestingUtils.getTestResourceFile("tinytest.jar"));
        Resource testJar2 = Resource.newResource(MavenTestingUtils.getTestResourceFile("tinytest_copy.jar"));
        AnnotationParser parser = new AnnotationParser();
        Set<Handler> emptySet = Collections.emptySet();
        parser.parse(emptySet, testJar);
        parser.parse(emptySet, testJar2);
        List<String> locations = parser.getParsedLocations("org.acme.ClassOne");
        Assert.assertNotNull(locations);
        Assert.assertEquals(2, locations.size());
        Assert.assertTrue(!(locations.get(0).equals(locations.get(1))));
    }
