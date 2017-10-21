    @Test
    public void testFileTooBig()
    throws Exception
    {
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 40, 1024, 30);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(_multi.getBytes()),
                                                            _contentType,
                                                             config,
                                                             _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = null;
        try
        {
            parts = mpis.getParts();
            fail("stuff.txt should have been larger than maxFileSize");
        }
        catch (IllegalStateException e)
        {
            assertTrue(e.getMessage().startsWith("Multipart Mime part"));
        }
    }
