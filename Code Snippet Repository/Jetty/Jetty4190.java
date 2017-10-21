    @Test
    public void testMatchAfterAndBefore() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        
        //add a programmatic one, isMatchAfter=true
        handler.addFilter(fh3);
        handler.addFilterMapping(fm3);
        FilterMapping[]  mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertEquals(1, mappings.length);
        assertTrue(fm3 == mappings[0]);
        
        //add a programmatic one, isMatchAfter=false
        handler.addFilter(fh4);
        handler.prependFilterMapping(fm4);
        mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertEquals(2, mappings.length);
        assertTrue(fm4 == mappings[0]);
        assertTrue(fm3 == mappings[1]);  
    }
