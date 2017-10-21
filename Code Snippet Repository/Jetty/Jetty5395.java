    @Test
    public void testIncludeOnly()
    {
        IncludeExclude<String> ie = new IncludeExclude<>();
        ie.include("foo");
        ie.include("bar");
        
        assertThat("IncludeExclude.size", ie.size(), is(2));
        assertEquals(false,ie.test(""));
        assertEquals(true,ie.test("foo"));
        assertEquals(true,ie.test("bar"));
        assertEquals(false,ie.test("foobar"));
    }
