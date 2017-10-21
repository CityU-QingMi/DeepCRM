    @Test
    public void testUncacheable() throws Exception
    {
        ResourceCollection rc = new ResourceCollection(new String[]{
                "../jetty-util/src/test/resources/org/eclipse/jetty/util/resource/one/",
                "../jetty-util/src/test/resources/org/eclipse/jetty/util/resource/two/",
                "../jetty-util/src/test/resources/org/eclipse/jetty/util/resource/three/"
        });

        Resource[] r = rc.getResources();
        MimeTypes mime = new MimeTypes();

        CachedContentFactory rc3 = new CachedContentFactory(null,r[2],mime,false,false,CompressedContentFormat.NONE);
        CachedContentFactory rc2 = new CachedContentFactory(rc3,r[1],mime,false,false,CompressedContentFormat.NONE)
        {
            @Override
            public boolean isCacheable(Resource resource)
            {
                return super.isCacheable(resource) && resource.getName().indexOf("2.txt")<0;
            }
        };

        CachedContentFactory rc1 = new CachedContentFactory(rc2,r[0],mime,false,false,CompressedContentFormat.NONE);

        assertEquals("1 - one", getContent(rc1, "1.txt"));
        assertEquals("2 - two", getContent(rc1, "2.txt"));
        assertEquals("3 - three", getContent(rc1, "3.txt"));

        assertEquals("1 - two", getContent(rc2, "1.txt"));
        assertEquals("2 - two", getContent(rc2, "2.txt"));
        assertEquals("3 - three", getContent(rc2, "3.txt"));

        assertEquals(null, getContent(rc3, "1.txt"));
        assertEquals("2 - three", getContent(rc3, "2.txt"));
        assertEquals("3 - three", getContent(rc3, "3.txt"));

    }
