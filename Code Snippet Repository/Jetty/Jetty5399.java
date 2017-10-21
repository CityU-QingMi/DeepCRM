    @Test
    public void testExcludeRegex()
    {
        IncludeExclude<String> ie = new IncludeExclude<>(RegexSet.class);
        ie.exclude("f..");
        ie.exclude("b((ar)|(oo))");
        
        assertEquals(2,ie.size());
        
        assertEquals(false,ie.test("foo"));
        assertEquals(false,ie.test("far"));
        assertEquals(false,ie.test("bar"));
        assertEquals(false,ie.test("boo"));
        assertEquals(true,ie.test(""));
        assertEquals(true,ie.test("foobar"));
        assertEquals(true,ie.test("xxx"));
    }
