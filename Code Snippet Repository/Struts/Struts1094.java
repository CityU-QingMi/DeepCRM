    public void testMatch() {
        Set<String> names = new HashSet<>();
        names.add("/bar");
        names.add("/foo/*/bar");
        names.add("/foo/*");
        names.add("/foo/*/jim/*");
        NamespaceMatcher matcher = new NamespaceMatcher(new WildcardHelper(), names);
        assertEquals(3, matcher.compiledPatterns.size());

        assertNull(matcher.match("/asd"));
        assertEquals("/foo/*", matcher.match("/foo/23").getPattern());
        assertEquals("/foo/*/bar", matcher.match("/foo/23/bar").getPattern());
        assertEquals("/foo/*/jim/*", matcher.match("/foo/23/jim/42").getPattern());
        assertNull(matcher.match("/foo/23/asd"));
    }
