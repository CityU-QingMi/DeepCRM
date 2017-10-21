    @Test
    public void testPartTmpFileDeletion () throws Exception
    {
        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);  
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(createMultipartRequestString("tptfd").getBytes()),
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);
        Collection<Part> parts = mpis.getParts();

        MultiPart part = (MultiPart)mpis.getPart("stuff");
        File stuff = ((MultiPartInputStreamParser.MultiPart)part).getFile();
        assertThat(stuff,notNullValue()); // longer than 100 bytes, should already be a tmp file
        assertThat (stuff.exists(), is(true));
        part.cleanUp();
        assertThat(stuff.exists(), is(false));  //tmp file was removed after cleanup
    }
