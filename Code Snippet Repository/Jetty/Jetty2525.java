    @Test
    public void testProgrammaticOverrideOfDefaultServletMapping() throws Exception
    {
        
        File quickstartXml = new File(webInf, "quickstart-web.xml");
        assertFalse(quickstartXml.exists());
        
        Server server = new Server();
        
        //generate a quickstart-web.xml
        QuickStartWebApp quickstart = new QuickStartWebApp();
        quickstart.setResourceBase(testDir.getAbsolutePath());
        quickstart.setPreconfigure(true);
        quickstart.setGenerateOrigin(true);
        ServletHolder fooHolder = new ServletHolder();
        fooHolder.setServlet(new FooServlet());
        fooHolder.setName("foo");
        quickstart.getServletHandler().addServlet(fooHolder);   
        quickstart.addEventListener(new FooContextListener());      
        server.setHandler(quickstart);
        server.start();
        server.stop();
        
        assertTrue(quickstartXml.exists());
        
        //now run the webapp again purely from the generated quickstart
        QuickStartWebApp webapp = new QuickStartWebApp();
        webapp.setResourceBase(testDir.getAbsolutePath());
        webapp.setPreconfigure(false);
        webapp.setClassLoader(Thread.currentThread().getContextClassLoader()); //only necessary for junit testing
        server.setHandler(webapp);
        
        server.start();
        
        //verify that FooServlet is now mapped to / and not the DefaultServlet
        ServletHolder sh = webapp.getServletHandler().getMappedServlet("/").getResource();
        assertNotNull(sh);
        assertEquals("foo", sh.getName());
        server.stop();
    }
