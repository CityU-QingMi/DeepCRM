    @Test
    public void testExistingFilterMappings() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        handler.addFilter(fh1);
        handler.addFilter(fh2);
        
        //add some ordinary filter mappings first
        handler.addFilterMapping(fm1);
        handler.addFilterMapping(fm2);
        
        FilterMapping[] mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertTrue(fm1 == mappings[0]);
        assertTrue(fm2 == mappings[1]);
        
        //do equivalent of FilterRegistration.addMappingForUrlPatterns(isMatchAfter=false)
        handler.addFilter(fh4);
        handler.prependFilterMapping(fm4);
        mappings = handler.getFilterMappings();
        assertEquals(3, mappings.length);
        assertTrue(fm4 == mappings[0]);
        
        //do equivalent of FilterRegistration.addMappingForUrlPatterns(isMatchAfter=true)
        handler.addFilter(fh5);
        handler.addFilterMapping(fm5);
        mappings = handler.getFilterMappings();
        assertEquals(4, mappings.length);
        assertTrue(fm5 == mappings[mappings.length-1]);
    }
