    @Test
    public void testNoBody()
            throws Exception
            {
        String body = "";

        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(body.getBytes()), 
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        try
        {
            mpis.getParts();
            fail ("Multipart missing body");
        }
        catch (IOException e)
        {
            assertTrue(e.getMessage().startsWith("Missing content"));
        }
    }
