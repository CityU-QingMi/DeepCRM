    @Test
    public void testBase64EncodedContent () throws Exception
    {
        String contentWithEncodedPart =
                "--AaB03x\r\n"+
                        "Content-disposition: form-data; name=\"other\"\r\n"+
                        "Content-Type: text/plain\r\n"+
                        "\r\n"+
                        "other" + "\r\n"+
                        "--AaB03x\r\n"+
                        "Content-disposition: form-data; name=\"stuff\"; filename=\"stuff.txt\"\r\n"+
                        "Content-Transfer-Encoding: base64\r\n"+
                        "Content-Type: application/octet-stream\r\n"+
                        "\r\n"+
                        B64Code.encode("hello jetty") + "\r\n"+                  
                        "--AaB03x\r\n"+
                        "Content-disposition: form-data; name=\"final\"\r\n"+
                        "Content-Type: text/plain\r\n"+
                        "\r\n"+
                        "the end" + "\r\n"+
                        "--AaB03x--\r\n";

        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(contentWithEncodedPart.getBytes()),
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();
        assertEquals(3, parts.size());

        Part p1 = mpis.getPart("other");
        assertNotNull(p1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IO.copy(p1.getInputStream(), baos);
        assertEquals("other", baos.toString("US-ASCII"));

        Part p2 = mpis.getPart("stuff");
        assertNotNull(p2);
        baos = new ByteArrayOutputStream();
        IO.copy(p2.getInputStream(), baos);
        assertEquals("hello jetty", baos.toString("US-ASCII"));
        
        Part p3 = mpis.getPart("final");
        assertNotNull(p3);
        baos = new ByteArrayOutputStream();
        IO.copy(p3.getInputStream(), baos);
        assertEquals("the end", baos.toString("US-ASCII"));
    }
