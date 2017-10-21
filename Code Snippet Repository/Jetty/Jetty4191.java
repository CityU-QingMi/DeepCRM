    @Test
    public void testMatchBeforeAndAfter() throws Exception
    {
        ServletHandler handler = new ServletHandler();
        
        //add a programmatic one, isMatchAfter=false
        handler.addFilter(fh3);
        handler.prependFilterMapping(fm3);
        FilterMapping[]  mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertEquals(1, mappings.length);
        assertTrue(fm3 == mappings[0]);
        
        //add a programmatic one, isMatchAfter=true
        handler.addFilter(fh4);
        handler.addFilterMapping(fm4);
        mappings = handler.getFilterMappings();
        assertNotNull(mappings);
        assertEquals(2, mappings.length);
        assertTrue(fm3 == mappings[0]); 
        assertTrue(fm4 == mappings[1]);
    }
