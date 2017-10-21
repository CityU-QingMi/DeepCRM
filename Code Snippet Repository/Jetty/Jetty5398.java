    @Test
    public void testIncludeRegex()
    {
        IncludeExclude<String> ie = new IncludeExclude<>(RegexSet.class);
        ie.include("f..");
        ie.include("b((ar)|(oo))");
        
        assertEquals(2,ie.size());
        assertEquals(false,ie.test(""));
        assertEquals(true,ie.test("foo"));
        assertEquals(true,ie.test("far"));
        assertEquals(true,ie.test("bar"));
        assertEquals(true,ie.test("boo"));
        assertEquals(false,ie.test("foobar"));
        assertEquals(false,ie.test("xxx"));
    }
