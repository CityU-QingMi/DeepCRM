    @Test
    public void testAllBeforeFilterMappings() throws Exception
    {
        ServletHandler handler = new ServletHandler(); 
        
        //do equivalent of FilterRegistration.addMappingForUrlPatterns(isMatchAfter=false)
        handler.addFilter(fh4);
        handler.prependFilterMapping(fm4);
        
        FilterMapping[] mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertEquals(1, mappings.length);
        
        //add another with isMatchAfter=false
        handler.addFilter(fh5);
        handler.prependFilterMapping(fm5);
        
        mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertEquals(2, mappings.length);
        
        assertTrue(fm4 == mappings[0]);
        assertTrue(fm5 == mappings[1]);
    }
