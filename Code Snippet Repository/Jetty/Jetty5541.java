    @Test
    public void testPartFileNotDeleted () throws Exception
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
        part.write("tptfd.txt");
        File tptfd = new File (_dirname+File.separator+"tptfd.txt");
        assertThat(tptfd.exists(), is(true));
        assertThat(stuff.exists(), is(false)); //got renamed
        part.cleanUp();
        assertThat(tptfd.exists(), is(true));  //explicitly written file did not get removed after cleanup
        tptfd.deleteOnExit(); //clean up test
    }
