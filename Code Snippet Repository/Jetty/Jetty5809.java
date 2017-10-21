    @Test
    public void testJarFileIsContainedIn ()
    throws Exception
    {
        String s = "jar:"+testResURI+"TestData/test.zip!/subdir/";
        Resource r = Resource.newResource(s);
        Resource container = Resource.newResource(testResURI+"TestData/test.zip");

        assertTrue(r instanceof JarFileResource);
        JarFileResource jarFileResource = (JarFileResource)r;

        assertTrue(jarFileResource.isContainedIn(container));

        container = Resource.newResource(testResURI+"TestData");
        assertFalse(jarFileResource.isContainedIn(container));
    }
