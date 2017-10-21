    public void testIfETag(String content) throws Exception
    {
        FS.ensureDirExists(docRoot);
        File file = new File(docRoot, "file.txt"); 

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        
        defholder.setInitParameter("maxCacheSize", "4096");
        defholder.setInitParameter("maxCachedFileSize", "25");
        defholder.setInitParameter("maxCachedFiles", "100");
        defholder.setInitParameter("etags", "true");

        String response;

        createFile(file, content);
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\n\r\n");

        assertResponseContains("200", response);
        assertResponseContains("ETag", response);
        String etag = getHeaderValue("ETag",response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-None-Match: "+etag+"\r\n\r\n");
        assertResponseContains("304", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-None-Match: wibble,"+etag+",wobble\r\n\r\n");
        assertResponseContains("304", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-None-Match: wibble\r\n\r\n");
        assertResponseContains("200", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-None-Match: wibble, wobble\r\n\r\n");
        assertResponseContains("200", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Match: "+etag+"\r\n\r\n");
        assertResponseContains("200", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Match: wibble,"+etag+",wobble\r\n\r\n");
        assertResponseContains("200", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Match: wibble\r\n\r\n");
        assertResponseContains("412", response);
        
        response = connector.getResponse("GET /context/file.txt HTTP/1.1\r\nHost:test\r\nConnection:close\r\nIf-Match: wibble, wobble\r\n\r\n");
        assertResponseContains("412", response);
        
    }
