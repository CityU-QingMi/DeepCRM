    @Test
    public void testIncludeExclude()
    {
        IncludeExclude<String> ie = new IncludeExclude<>();
        ie.include("foo");
        ie.include("bar");
        ie.exclude("bar");
        ie.exclude("xxx");
        
        assertEquals(4,ie.size());
        
        assertEquals(true,ie.test("foo"));
        assertEquals(false,ie.test("bar"));
        assertEquals(false,ie.test(""));
        assertEquals(false,ie.test("foobar"));
        assertEquals(false,ie.test("xxx"));
    }
