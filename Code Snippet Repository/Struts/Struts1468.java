    @Test
    public void testMatch() {
        NamedVariablePatternMatcher matcher = new NamedVariablePatternMatcher();

        Map<String, String> vars = new HashMap<>();
        CompiledPattern pattern = new CompiledPattern(Pattern.compile("foo([^/]+)"), Arrays.asList("bar"));

        assertTrue(matcher.match(vars, "foobaz", pattern));
        assertEquals("baz", vars.get("bar"));
    }
