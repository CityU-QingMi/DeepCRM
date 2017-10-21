    protected static void testHttpServiceGreetings(BundleContext bundleContext, String protocol, int port) throws Exception
    {
        assertActiveBundle(bundleContext, "org.eclipse.jetty.osgi.boot");

        assertActiveBundle(bundleContext, "org.eclipse.jetty.osgi.httpservice");
        assertActiveBundle(bundleContext, "org.eclipse.equinox.http.servlet");

        // in the OSGi world this would be bad code and we should use a bundle
        // tracker.
        // here we purposely want to make sure that the httpService is actually
        // ready.
        ServiceReference sr = bundleContext.getServiceReference(HttpService.class.getName());
        Assert.assertNotNull("The httpServiceOSGiBundle is started and should " + "have deployed a service reference for HttpService", sr);
        HttpService http = (HttpService) bundleContext.getService(sr);
        http.registerServlet("/greetings", new HttpServlet()
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                resp.getWriter().write("Hello");
            }
        }, null, null);

        // now test the servlet
        HttpClient client = protocol.equals("https") ? new HttpClient(newSslContextFactory()) : new HttpClient();
        try
        {
            client.start();
            ContentResponse response = client.GET(protocol + "://127.0.0.1:" + port + "/greetings");
            Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

            String content = new String(response.getContent());
            Assert.assertEquals("Hello", content);
        }
        finally
        {
            client.stop();
        }
    }
