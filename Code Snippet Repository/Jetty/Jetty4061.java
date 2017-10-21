    @Test
    public void testDirectFromResourceHttpContent() throws Exception
    {
        if (!OS.IS_LINUX)
            return;
        
        
        
        FS.ensureDirExists(docRoot);
        context.setBaseResource(Resource.newResource(docRoot));
        
        File index = new File(docRoot, "index.html");
        createFile(index, "<h1>Hello World</h1>");

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("dirAllowed", "true");
        defholder.setInitParameter("redirectWelcome", "false");
        defholder.setInitParameter("useFileMappedBuffer", "true");
        defholder.setInitParameter("welcomeServlets", "exact");
        defholder.setInitParameter("gzip", "false");
        defholder.setInitParameter("resourceCache","resourceCache");

        String response;

        response = connector.getResponse("GET /context/index.html HTTP/1.0\r\n\r\n");
        assertResponseContains("<h1>Hello World</h1>", response);
        
        ResourceContentFactory factory = (ResourceContentFactory)context.getServletContext().getAttribute("resourceCache");
        
        HttpContent content = factory.getContent("/index.html",200);
        ByteBuffer buffer = content.getDirectBuffer();
        Assert.assertTrue(buffer.isDirect());        
        content = factory.getContent("/index.html",5);
        buffer = content.getDirectBuffer();
        Assert.assertTrue(buffer==null);        
    }
