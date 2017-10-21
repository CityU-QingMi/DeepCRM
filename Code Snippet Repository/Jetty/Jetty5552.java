    @Test
    public void testWriteFilesIfContentDispositionFilename ()
    throws Exception
    {
        String s = "--AaB03x\r\n"+
                "content-disposition: form-data; name=\"field1\"; filename=\"frooble.txt\"\r\n"+
                "\r\n"+
                "Joe Blow\r\n"+
                "--AaB03x\r\n"+
                "content-disposition: form-data; name=\"stuff\"\r\n"+
                "Content-Type: text/plain\r\n"+
                "\r\n"+"sss"+
                "aaa"+"\r\n" +
                "--AaB03x--\r\n";
        //all default values for multipartconfig, ie file size threshold 0
        MultipartConfigElement config = new MultipartConfigElement(_dirname);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(s.getBytes()),
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir); 
        mpis.setDeleteOnExit(true);
        mpis.setWriteFilesWithFilenames(true);
        Collection<Part> parts = mpis.getParts();
        assertThat(parts.size(), is(2));
        Part field1 = mpis.getPart("field1"); //has a filename, should be written to a file
        File f = ((MultiPartInputStreamParser.MultiPart)field1).getFile();
        assertThat(f,notNullValue()); // longer than 100 bytes, should already be a tmp file
        
        Part stuff = mpis.getPart("stuff");
        f = ((MultiPartInputStreamParser.MultiPart)stuff).getFile(); //should only be in memory, no filename
        assertThat(f, nullValue());
    }
