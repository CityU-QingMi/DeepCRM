    @Test
    public void testAllAfterFilterMappings() throws Exception
    {
        ServletHandler handler = new ServletHandler(); 
        //do equivalent of FilterRegistration.addMappingForUrlPatterns(isMatchAfter=true)
        handler.addFilter(fh4);
        handler.addFilterMapping(fm4);
        FilterMapping[] mappings = handler.getFilterMappings();
        assertEquals(1, mappings.length);
        assertTrue(fm4 == mappings[0]);
        
        //do equivalent of FilterRegistration.addMappingForUrlPatterns(isMatchAfter=true)
        handler.addFilter(fh5);
        handler.addFilterMapping(fm5);
        mappings = handler.getFilterMappings();
        assertEquals(2, mappings.length);
        assertTrue(fm4 == mappings[0]);
        assertTrue(fm5 == mappings[1]);
    }
