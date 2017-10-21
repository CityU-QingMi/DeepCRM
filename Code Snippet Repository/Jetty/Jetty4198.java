    @Test
    public void testDuplicateMappingsWithDefaults() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        handler.setAllowDuplicateMappings(false);
        handler.addServlet(sh1);
        handler.addServlet(sh3);
        handler.updateNameMappings();

        handler.addServletMapping(sm3);
        handler.addServletMapping(sm1);
       

        handler.updateMappings();

        MappedResource<ServletHolder> entry=handler.getMappedServlet("/foo/*");
        assertNotNull(entry);
        assertEquals("s1", entry.getResource().getName());
    }
