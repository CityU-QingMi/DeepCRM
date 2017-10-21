    @Test
    public void testDuplicateMappingsForbidden() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        handler.setAllowDuplicateMappings(false);
        handler.addServlet(sh1);
        handler.addServlet(sh2);
        handler.updateNameMappings();
        
        handler.addServletMapping(sm1);
        handler.addServletMapping(sm2);

        try
        {
            handler.updateMappings();
        }
        catch (IllegalStateException e)
        {
            //expected error
        }
    }
