    public void testIfModified(String content) throws Exception
    {
        
        
        FS.ensureDirExists(docRoot);
        File file = new File(docRoot, "file.txt");

        

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        
        defholder.setInitParameter("maxCacheSize", "4096");
        defholder.setInitParameter("maxCachedFileSize", "25");
        defholder.setInitParameter("maxCachedFiles", "100");

        String response = connector.getResponse("GET /context/file.txt HTTP/1.0\r\n\r\n");
        assertResponseContains("404", response);

        createFile(file, content);
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\n\r\n");

        assertResponseContains("200", response);
        assertResponseContains("Last-Modified", response);
        String last_modified = getHeaderValue("Last-Modified",response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Modified-Since: "+last_modified+"\r\n\r\n");
        assertResponseContains("304", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Modified-Since: "+DateGenerator.formatDate(System.currentTimeMillis()-10000)+"\r\n\r\n");
        assertResponseContains("200", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Modified-Since: "+DateGenerator.formatDate(System.currentTimeMillis()+10000)+"\r\n\r\n");
        assertResponseContains("304", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Unmodified-Since: "+DateGenerator.formatDate(System.currentTimeMillis()+10000)+"\r\n\r\n");
        assertResponseContains("200", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Unmodified-Since: "+DateGenerator.formatDate(System.currentTimeMillis()-10000)+"\r\n\r\n");
        assertResponseContains("412", response);
    }
