    @Test
    public void testCustomCompressionFormats() throws Exception
    {
        
        
        FS.ensureDirExists(docRoot);
        File file0 = new File(docRoot, "data0.txt");
        createFile(file0, "Hello Text 0");
        File file0br = new File(docRoot, "data0.txt.br");
        createFile(file0br, "fake brotli");
        File file0gz = new File(docRoot, "data0.txt.gz");
        createFile(file0gz, "fake gzip");

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("precompressed", "bzip2=.bz2,gzip=.gz,br=.br");
        defholder.setInitParameter("resourceBase", docRoot.getAbsolutePath());

        String response = connector.getResponse("GET /context/data0.txt HTTP/1.0\r\nHost:localhost:8080\r\nAccept-Encoding:bzip2, br, gzip\r\n\r\n");
        assertResponseContains("Content-Length: 9", response);
        assertResponseContains("fake gzip",response);
        assertResponseContains("Content-Type: text/plain",response);
        assertResponseContains("Vary: Accept-Encoding",response);
        assertResponseContains("Content-Encoding: gzip",response);
    }
