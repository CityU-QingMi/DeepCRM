    @Test
    public void testWelcomeDirWithQuestion() throws Exception
    {
        
        
        FS.ensureDirExists(docRoot);
        context.setBaseResource(Resource.newResource(docRoot));

        File dir = new File(docRoot, "dir?");
        assumeTrue("FileSystem should support question dirs", dir.mkdirs());

        File index = new File(dir, "index.html");
        createFile(index, "<h1>Hello Index</h1>");

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("dirAllowed", "false");
        defholder.setInitParameter("redirectWelcome", "true");
        defholder.setInitParameter("welcomeServlets", "false");
        defholder.setInitParameter("gzip", "false");

        String response = connector.getResponse("GET /context/dir%3F HTTP/1.0\r\n\r\n");
        assertResponseContains("Location: http://0.0.0.0/context/dir%3F/", response);

        response = connector.getResponse("GET /context/dir%3F/ HTTP/1.0\r\n\r\n");
        assertResponseContains("Location: http://0.0.0.0/context/dir%3F/index.html", response);
    }
