    @Test
    public void testIncludeExcludeRegex()
    {
        IncludeExclude<String> ie = new IncludeExclude<>(RegexSet.class);
        ie.include(".*[aeiou].*");
        ie.include("[AEIOU].*");
        ie.exclude("f..");
        ie.exclude("b((ar)|(oo))");
        
        assertEquals(4,ie.size());
        assertEquals(false,ie.test("foo"));
        assertEquals(false,ie.test("far"));
        assertEquals(false,ie.test("bar"));
        assertEquals(false,ie.test("boo"));
        assertEquals(false,ie.test(""));
        assertEquals(false,ie.test("xxx"));

        assertEquals(true,ie.test("foobar"));
        assertEquals(true,ie.test("Ant"));
    }
