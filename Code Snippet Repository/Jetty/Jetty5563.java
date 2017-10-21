    @Test
    public void testLeadingWhitespaceBodyWithCRLF()
    throws Exception
    {
        String body = "              \n\n\n\r\n\r\n\r\n\r\n"+
                "--AaB03x\r\n"+
                "content-disposition: form-data; name=\"field1\"\r\n"+
                "\r\n"+
                "Joe Blow\r\n"+
                "--AaB03x\r\n"+
                "content-disposition: form-data; name=\"stuff\"; filename=\"" + "foo.txt" + "\"\r\n"+
                "Content-Type: text/plain\r\n"+
                "\r\n"+"aaaa"+
                "bbbbb"+"\r\n" +
                "--AaB03x--\r\n";


        MultipartConfigElement config = new MultipartConfigElement(_dirname, 1024, 3072, 50);
        MultiPartInputStreamParser mpis = new MultiPartInputStreamParser(new ByteArrayInputStream(body.getBytes()),
                                                                         _contentType,
                                                                         config,
                                                                         _tmpDir);
        mpis.setDeleteOnExit(true);

        Collection<Part> parts =    mpis.getParts();
        assertThat(parts, notNullValue());
        assertThat(parts.size(), is(2));
        Part field1 = mpis.getPart("field1");
        assertThat(field1, notNullValue());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IO.copy(field1.getInputStream(), baos);
        assertThat(baos.toString("US-ASCII"), is("Joe Blow"));
        
        Part stuff = mpis.getPart("stuff");
        assertThat(stuff, notNullValue());
        baos = new ByteArrayOutputStream();
        IO.copy(stuff.getInputStream(), baos);
        assertTrue(baos.toString("US-ASCII").contains("aaaa"));
    }
