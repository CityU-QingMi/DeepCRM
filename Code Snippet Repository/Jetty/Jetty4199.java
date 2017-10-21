    @Test
    public void testDuplicateMappingsSameServlet() throws Exception
    {
        ServletHolder sh4 = new ServletHolder();
       
        sh4.setName("s1");
        
        ServletMapping sm4 = new ServletMapping();
        sm4.setPathSpec("/foo/*");
        sm4.setServletName("s1");
        
        ServletHandler handler = new ServletHandler();
        handler.setAllowDuplicateMappings(true);
        handler.addServlet(sh1);
        handler.addServlet(sh4);
        handler.updateNameMappings();

        handler.addServletMapping(sm1);
        handler.addServletMapping(sm4);
       

        handler.updateMappings();
    }
