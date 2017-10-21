    public void testCompileNamedParams1() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/some/{test1:.+}/{test2:.*}");
        assertNotNull(expr);

        //params
        Map<Integer, String> params = expr.getParams();
        assertNotNull(params);
        assertEquals(2, params.size());
        assertEquals("test1", params.get(1));
        assertEquals("test2", params.get(2));

        //pattern
        Pattern pattern = expr.getPattern();
        assertNotNull(pattern);
        assertEquals("/some/(.+)/(.*)", pattern.pattern());
    }
