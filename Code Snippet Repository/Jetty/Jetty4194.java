    @Test
    public void testAddFilterIgnoresDuplicates2() throws Exception
    {

        ServletHandler handler = new ServletHandler();
        FilterHolder h = new FilterHolder();
        h.setName("x");
        FilterMapping m = new FilterMapping();
        m.setPathSpec("/*");
        m.setFilterHolder(h);
        
        
        handler.addFilter(h,m);
        FilterHolder[] holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders[0]==h);
        
        
        FilterMapping m2 = new FilterMapping();
        m2.setPathSpec("/*");
        m2.setFilterHolder(h);
        handler.addFilter(h, m2);
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 1);
        assertTrue(holders[0] == h);
        
        FilterHolder h2 = new FilterHolder();
        h2.setName("x"); //not allowed by servlet spec, just here to test object equality
        FilterMapping m3 = new FilterMapping();
        m3.setPathSpec("/*");
        m3.setFilterHolder(h);
        
        handler.addFilter(h2, m3);
        holders = handler.getFilters();
        assertNotNull(holders);
        assertTrue(holders.length == 2);
        assertTrue(holders[1] == h2);
    }
