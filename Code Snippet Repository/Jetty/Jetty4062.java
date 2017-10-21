    @Test
    public void testDefaultBrotliOverGzip() throws Exception
    {
        
        
        FS.ensureDirExists(docRoot);
        File file0 = new File(docRoot, "data0.txt");
        createFile(file0, "Hello Text 0");
        File file0br = new File(docRoot, "data0.txt.br");
        createFile(file0br, "fake brotli");
        File file0gz = new File(docRoot, "data0.txt.gz");
        createFile(file0gz, "fake gzip");

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("precompressed", "true");
        defholder.setInitParameter("resourceBase", docRoot.getAbsolutePath());

        String response = connector.getResponse("GET /context/data0.txt HTTP/1.0\r\nHost:localhost:8080\r\nAccept-Encoding:gzip, compress, br\r\n\r\n");
        assertResponseContains("Content-Length: 11", response);
        assertResponseContains("fake br",response);
        assertResponseContains("Content-Type: text/plain",response);
        assertResponseContains("Vary: Accept-Encoding",response);
        assertResponseContains("Content-Encoding: br",response);

        response = connector.getResponse("GET /context/data0.txt HTTP/1.0\r\nHost:localhost:8080\r\nAccept-Encoding:gzip, compress, br;q=0.9\r\n\r\n");
        assertResponseContains("Content-Length: 9", response);
        assertResponseContains("fake gzip",response);
        assertResponseContains("Content-Type: text/plain",response);
        assertResponseContains("Vary: Accept-Encoding",response);
        assertResponseContains("Content-Encoding: gzip",response);
    }
