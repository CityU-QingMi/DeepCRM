    @Test
    public void testCharsetEncoding () throws Exception
    {
        String contentType = "multipart/form-data; boundary=TheBoundary; charset=ISO-8859-1";
        String str = "--TheBoundary\r"+
                "content-disposition: form-data; name=\"field1\"\r"+
                "\r"+
                "\nJoe Blow\n"+ 
                "\r"+
                "--TheBoundary--\r";
        
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(str.getBytes()),
                                                                         contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();
        assertThat(parts.size(), is(1));
    }
