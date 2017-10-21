    @Test
    public void testAllNonProgrammaticFilterMappings() throws Exception
    {
        ServletHandler handler = new ServletHandler(); 
        handler.addFilter(fh1);
        handler.addFilter(fh2);
        
        //add some ordinary filter mappings
        handler.addFilterMapping(fm1);
        handler.addFilterMapping(fm2);
        
        FilterMapping[] mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertTrue(fm1 == mappings[0]);
        assertTrue(fm2 == mappings[1]);
        
        //add another ordinary mapping
        FilterHolder of1 = new FilterHolder(new Source (Source.Origin.DESCRIPTOR, "foo.xml"));
        FilterMapping ofm1 = new FilterMapping();
        ofm1.setFilterHolder(of1);
        ofm1.setPathSpec("/*");
        handler.addFilter(of1);
        handler.addFilterMapping(ofm1);
        
        mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertTrue(fm1 == mappings[0]);
        assertTrue(fm2 == mappings[1]);
        assertTrue(ofm1 == mappings[2]);
    }
