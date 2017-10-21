    @Test
    public void testMultiSameNames ()
    throws Exception
    {
        String sameNames =  "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"stuff\"; filename=\"stuff1.txt\"\r\n"+
        "Content-Type: text/plain\r\n"+
        "\r\n"+
        "00000\r\n"+
        "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"stuff\"; filename=\"stuff2.txt\"\r\n"+
        "Content-Type: text/plain\r\n"+
        "\r\n"+
        "110000000000000000000000000000000000000000000000000\r\n"+
        "--AaB03x--\r\n";

        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(sameNames.getBytes()),
                                                             _contentType,
                                                             config,
                                                             _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();
        assertEquals(2, parts.size());
        for (Part p:parts)
            assertEquals("stuff", p.getName());

        //if they all have the name name, then only retrieve the first one
        Part p = mpis.getPart("stuff");
        assertNotNull(p);
        assertEquals(5, p.getSize());
    }
