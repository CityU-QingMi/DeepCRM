    @Test
    public void testBadlyEncodedFilename() throws Exception
    {
        
        String contents =  "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"stuff\"; filename=\"" +"Taken on Aug 22 \\ 2012.jpg"  + "\"\r\n"+
        "Content-Type: text/plain\r\n"+
        "\r\n"+"stuff"+
        "aaa"+"\r\n" +
        "--AaB03x--\r\n";
        
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(contents.getBytes()),
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();
        assertThat(parts.size(), is(1));
        assertThat(((MultiPartInputStreamParser.MultiPart)parts.iterator().next()).getSubmittedFileName(), is("Taken on Aug 22 \\ 2012.jpg"));
    }
