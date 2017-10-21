    @Test
    public void testAddFilterWithMappingIgnoresDuplicateFilters() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        FilterHolder h = new FilterHolder();
        h.setName("x");
 
        
        
        handler.addFilterWithMapping(h,"/*", 0);
        FilterHolder[] holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders[0]==h);
        
        handler.addFilterWithMapping(h, "/*", 1);
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 1);
        assertTrue(holders[0] == h);
        
        FilterHolder h2 = new FilterHolder();
        h2.setName("x"); //not allowed by servlet spec, just here to test object equality
        
        handler.addFilterWithMapping(h2, "/*", 0);
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 2);
        assertTrue(holders[1] == h2);
    }
