    @Test
    public void testNoextension() throws Exception
    {
        ResourceCollection rc = new ResourceCollection(new String[]{
                "../jetty-util/src/test/resources/org/eclipse/jetty/util/resource/four/"
        });

        Resource[] resources = rc.getResources();
        MimeTypes mime = new MimeTypes();

        CachedContentFactory cache = new CachedContentFactory(null,resources[0],mime,false,false,CompressedContentFormat.NONE);

        assertEquals("4 - four", getContent(cache, "four.txt"));
        assertEquals("4 - four (no extension)", getContent(cache, "four"));
    }
