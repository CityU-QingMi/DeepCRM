    @Test
    public void testCompile() {
        NamedVariablePatternMatcher matcher = new NamedVariablePatternMatcher();

        assertNull(matcher.compilePattern(null));
        assertNull(matcher.compilePattern(""));

        CompiledPattern pattern = matcher.compilePattern("foo");
        assertEquals("foo", pattern.getPattern().pattern());

        pattern = matcher.compilePattern("foo{jim}");
        assertEquals("foo([^/]+)", pattern.getPattern().pattern());
        assertEquals("jim", pattern.getVariableNames().get(0));

        pattern = matcher.compilePattern("foo{jim}/{bob}");
        assertEquals("foo([^/]+)/([^/]+)", pattern.getPattern().pattern());
        assertEquals("jim", pattern.getVariableNames().get(0));
        assertEquals("bob", pattern.getVariableNames().get(1));
        assertTrue(pattern.getPattern().matcher("foostar/jie").matches());
        assertFalse(pattern.getPattern().matcher("foo/star/jie").matches());
    }
