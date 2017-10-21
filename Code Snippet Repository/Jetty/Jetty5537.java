    @Test
    public void testRequestTooBig ()
    throws Exception
    {
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 60, 100, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(_multi.getBytes()),
                                                            _contentType,
                                                             config,
                                                             _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = null;
        try
        {
            parts = mpis.getParts();
            fail("Request should have exceeded maxRequestSize");
        }
        catch (IllegalStateException e)
        {
            assertTrue(e.getMessage().startsWith("Request exceeds maxRequestSize"));
        }
    }
