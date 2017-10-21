    @Test
    public void testConcatenation() throws Exception
    {
        String contextPath = "";
        ServletContextHandler context = new ServletContextHandler(server, contextPath);
        server.setHandler(context);
        String concatPath = "/concat";
        context.addServlet(ConcatServlet.class, concatPath);
        ServletHolder resourceServletHolder = new ServletHolder(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                String includedURI = (String)request.getAttribute(RequestDispatcher.INCLUDE_REQUEST_URI);
                response.getOutputStream().println(includedURI);
            }
        });
        context.addServlet(resourceServletHolder, "/resource/*");
        server.start();

        String resource1 = "/resource/one.js";
        String resource2 = "/resource/two.js";
        String uri = contextPath + concatPath + "?" + resource1 + "&" + resource2;
        String request = "" +
                "GET " + uri + " HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        String response = connector.getResponse(request);
        try (BufferedReader reader = new BufferedReader(new StringReader(response)))
        {
            while (true)
            {
                String line = reader.readLine();
                if (line == null)
                    Assert.fail();
                if (line.trim().isEmpty())
                    break;
            }
            Assert.assertEquals(resource1, reader.readLine());
            Assert.assertEquals(resource2, reader.readLine());
            Assert.assertNull(reader.readLine());
        }
    }
