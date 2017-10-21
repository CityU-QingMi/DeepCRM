    public void testMatch0() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/some/{test}");
        
        Map<String, String> values = new HashMap<String, String>();
        
        assertTrue(matcher.match(values, "/some/val", expr));
        assertEquals(3, values.size());
        assertEquals("val", values.get("test"));
        assertEquals("val", values.get("1"));
        
        assertEquals("/some/val", values.get("0"));
    }
