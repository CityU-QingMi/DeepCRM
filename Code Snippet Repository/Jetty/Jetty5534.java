    @Test
    public void testBadMultiPartRequest()
    throws Exception
    {
        String boundary = "X0Y0";
        String str = "--" + boundary + "\r\n"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"test.upload\"\r\n"+
        "Content-Type: application/octet-stream\r\n\r\n"+
        "How now brown cow."+
        "\r\n--" + boundary + "-\r\n\r\n";

        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(str.getBytes()), 
                                                                         "multipart/form-data, boundary="+boundary,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        try
        {
            mpis.getParts();
            fail ("Multipart incomplete");
        }
        catch (IOException e)
        {
            assertTrue(e.getMessage().startsWith("Incomplete"));
        }
    }
