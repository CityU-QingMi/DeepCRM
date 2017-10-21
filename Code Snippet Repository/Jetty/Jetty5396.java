    @Test
    public void testExcludeOnly()
    {
        IncludeExclude<String> ie = new IncludeExclude<>();
        ie.exclude("foo");
        ie.exclude("bar");
        
        assertEquals(2,ie.size());
        
        assertEquals(false,ie.test("foo"));
        assertEquals(false,ie.test("bar"));
        assertEquals(true,ie.test(""));
        assertEquals(true,ie.test("foobar"));
        assertEquals(true,ie.test("wibble"));
    }
