    @Test
    public void testDuplicateMappingsAllowed() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        handler.setAllowDuplicateMappings(true);
        handler.addServlet(sh1);
        handler.addServlet(sh2);
        handler.updateNameMappings();
        
        handler.addServletMapping(sm1);
        handler.addServletMapping(sm2);
        handler.updateMappings();
        
       MappedResource<ServletHolder> entry=handler.getMappedServlet("/foo/*");
       assertNotNull(entry);
       assertEquals("s2", entry.getResource().getName());
    }
