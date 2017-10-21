    @Test
    public void testAddFilterWithMappingIngoresDuplicateFilters2 () throws Exception
    {
        ServletHandler handler = new ServletHandler();
        FilterHolder h = new FilterHolder();
        h.setName("x");
 
        
        
        handler.addFilterWithMapping(h,"/*", EnumSet.allOf(DispatcherType.class));
        FilterHolder[] holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders[0]==h);
        
        handler.addFilterWithMapping(h, "/x", EnumSet.allOf(DispatcherType.class));
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 1);
        assertTrue(holders[0] == h);
        
        FilterHolder h2 = new FilterHolder();
        h2.setName("x"); //not allowed by servlet spec, just here to test object equality
        
        handler.addFilterWithMapping(h2, "/*", EnumSet.allOf(DispatcherType.class));
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 2);
        assertTrue(holders[1] == h2);
    }
