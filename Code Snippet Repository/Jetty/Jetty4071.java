    @Test
    public void testWelcome() throws Exception
    {
        FS.ensureDirExists(docRoot);

        File dir = new File(docRoot, "dir");
        assertTrue(dir.mkdirs());
        File inde = new File(dir, "index.htm");
        File index = new File(dir, "index.html");
        
        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("dirAllowed", "false");
        defholder.setInitParameter("redirectWelcome", "false");
        defholder.setInitParameter("welcomeServlets", "false");
        defholder.setInitParameter("gzip", "false");
        
        defholder.setInitParameter("maxCacheSize", "1024000");
        defholder.setInitParameter("maxCachedFileSize", "512000");
        defholder.setInitParameter("maxCachedFiles", "100");

        @SuppressWarnings("unused")
        ServletHolder jspholder = context.addServlet(NoJspServlet.class, "*.jsp");

        String response = connector.getResponse("GET /context/dir/ HTTP/1.0\r\n\r\n");
        assertResponseContains("403", response);

        createFile(index, "<h1>Hello Index</h1>");
        response = connector.getResponse("GET /context/dir/ HTTP/1.0\r\n\r\n");
        assertResponseContains("<h1>Hello Index</h1>", response);

        createFile(inde, "<h1>Hello Inde</h1>");
        response = connector.getResponse("GET /context/dir/ HTTP/1.0\r\n\r\n");
        assertResponseContains("<h1>Hello Index</h1>", response);

        assertTrue(index.delete());
        response = connector.getResponse("GET /context/dir/ HTTP/1.0\r\n\r\n");
        assertResponseContains("<h1>Hello Inde</h1>", response);

        assertTrue(inde.delete());
        response = connector.getResponse("GET /context/dir/ HTTP/1.0\r\n\r\n");
        assertResponseContains("403", response);
    }
