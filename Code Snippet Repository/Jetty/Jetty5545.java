    @Test
    public void testFinalBoundaryOnly()
    throws Exception
    {
        String delimiter = "\r\n";
        final String boundary = "MockMultiPartTestBoundary";


        // Malformed multipart request body containing only an arbitrary string of text, followed by the final boundary marker, delimited by empty lines.
        String str =
                delimiter +
                "Hello world" +
                delimiter +        // Two delimiter markers, which make an empty line.
                delimiter +
                "--" + boundary + "--" + delimiter; 

        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(str.getBytes()),
                                                                         "multipart/form-data, boundary="+boundary,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();
        assertTrue(mpis.getParts().isEmpty());
    }
