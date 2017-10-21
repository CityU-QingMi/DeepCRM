    @Test
    public void testAddFilterIgnoresDuplicates() throws Exception
    {

        ServletHandler handler = new ServletHandler();
        FilterHolder h = new FilterHolder();
        h.setName("x");
        handler.addFilter(h);
        FilterHolder[] holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders[0]==h);
        
        handler.addFilter(h);
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 1);
        assertTrue(holders[0] == h);
        
        FilterHolder h2 = new FilterHolder();
        h2.setName("x"); //not allowed by servlet spec, just here to test object equality
        handler.addFilter(h2);
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 2);
        assertTrue(holders[1] == h2);
    }
