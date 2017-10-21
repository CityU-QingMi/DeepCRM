    @Test
    public void testWelcomeExactServlet() throws Exception
    {
        
        
        FS.ensureDirExists(docRoot);
        File inde = new File(docRoot, "index.htm");
        File index = new File(docRoot, "index.html");

        

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("dirAllowed", "false");
        defholder.setInitParameter("redirectWelcome", "false");
        defholder.setInitParameter("welcomeServlets", "exact");
        defholder.setInitParameter("gzip", "false");
        

        ServletHolder jspholder = context.addServlet(NoJspServlet.class, "*.jsp");
        context.addServlet(jspholder, "/index.jsp");

        String response;

        response = connector.getResponse("GET /context/ HTTP/1.0\r\n\r\n");
        assertResponseContains("JSP support not configured", response);

        createFile(index, "<h1>Hello Index</h1>");
        response = connector.getResponse("GET /context/ HTTP/1.0\r\n\r\n");
        assertResponseContains("<h1>Hello Index</h1>", response);

        createFile(inde, "<h1>Hello Inde</h1>");
        response = connector.getResponse("GET /context/ HTTP/1.0\r\n\r\n");
        assertResponseContains("<h1>Hello Index</h1>", response);

        // In Windows it's impossible to delete files that are somehow in use
        // Avoid to fail the test if we're on Windows
        if (!OS.IS_WINDOWS)
        {
            deleteFile(index);
            response = connector.getResponse("GET /context/ HTTP/1.0\r\n\r\n");
            assertResponseContains("<h1>Hello Inde</h1>", response);

            deleteFile(inde);
            response = connector.getResponse("GET /context/ HTTP/1.0\r\n\r\n");
            assertResponseContains("JSP support not configured", response);
        }
    }
