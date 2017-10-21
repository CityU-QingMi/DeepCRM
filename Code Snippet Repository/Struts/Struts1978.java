    public void testCompile1() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/{test}/some/{test1}/");
        assertNotNull(expr);

        //params
        Map<Integer, String> params = expr.getParams();
        assertNotNull(params);
        assertEquals(2, params.size());
        assertEquals("test", params.get(1));
        assertEquals("test1", params.get(2));

        //pattern
        Pattern pattern = expr.getPattern();
        assertNotNull(pattern);
        assertEquals("/(.*?)/some/(.*?)/", pattern.pattern());
    }
