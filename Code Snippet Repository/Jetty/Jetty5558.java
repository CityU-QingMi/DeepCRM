    @Test
    public void testNonMultiPartRequest()
    throws Exception
    {
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(_multi.getBytes()),
                                                            "Content-type: text/plain",
                                                             config,
                                                            _tmpDir);
        mpis.setDeleteOnExit(true);
        assertTrue(mpis.getParts().isEmpty());
    }
