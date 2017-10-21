    @Test
    public void testJarFileLastModified ()
    throws Exception
    {
        String s = "jar:"+testResURI+"TestData/test.zip!/subdir/numbers";

        try(ZipFile zf = new ZipFile(MavenTestingUtils.getTestResourceFile("TestData/test.zip")))
        {
            long last = zf.getEntry("subdir/numbers").getTime();

            Resource r = Resource.newResource(s);
            assertEquals(last,r.lastModified());
        }
    }
