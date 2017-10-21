    @Test
    public void testCopyTo() throws Exception
    {
        ResourceCollection rc = new ResourceCollection(new String[]{
                "src/test/resources/org/eclipse/jetty/util/resource/one/",
                "src/test/resources/org/eclipse/jetty/util/resource/two/",
                "src/test/resources/org/eclipse/jetty/util/resource/three/"
        });

        File dest = File.createTempFile("copyto",null);
        if (dest.exists())
            dest.delete();
        dest.mkdir();
        dest.deleteOnExit();
        rc.copyTo(dest);

        Resource r = Resource.newResource(dest.toURI());
        assertEquals("1 - one", getContent(r, "1.txt"));
        assertEquals("2 - two", getContent(r, "2.txt"));
        assertEquals("3 - three", getContent(r, "3.txt"));
        r = r.addPath("dir");
        assertEquals("1 - one", getContent(r, "1.txt"));
        assertEquals("2 - two", getContent(r, "2.txt"));
        assertEquals("3 - three", getContent(r, "3.txt"));

        IO.delete(dest);
    }
