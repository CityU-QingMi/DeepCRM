    public void testCompile0() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/some/{test}");
        assertNotNull(expr);

        //params
        Map<Integer, String> params = expr.getParams();
        assertNotNull(params);
        assertEquals(1, params.size());
        assertEquals("test", params.get(1));

        //pattern
        Pattern pattern = expr.getPattern();
        assertNotNull(pattern);
        assertEquals("/some/(.*?)", pattern.pattern());
    }
