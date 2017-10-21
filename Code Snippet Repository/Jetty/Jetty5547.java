    @Test
    public void testBufferOverflowNoCRLF () throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("--AaB03x".getBytes());
        for (int i=0; i< 8500; i++) //create content that will overrun default buffer size of BufferedInputStream
        {
            baos.write('a');
        }
        
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(baos.toByteArray()), 
                                                             _contentType,
                                                             config,
                                                             _tmpDir);
        mpis.setDeleteOnExit(true);
        try
        {
            mpis.getParts();
            fail ("Multipart buffer overrun");
        }
        catch (IOException e)
        {
            assertTrue(e.getMessage().startsWith("Buffer size exceeded"));
        }

    }
