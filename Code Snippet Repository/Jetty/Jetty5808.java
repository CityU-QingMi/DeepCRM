    @Test
    public void testJarFileGetAllResoures()
    throws Exception
    {
        String s = "jar:"+testResURI+"TestData/test.zip!/subdir/";
        Resource r = Resource.newResource(s);
        Collection<Resource> deep=r.getAllResources();
        
        assertEquals(4, deep.size());
    }
