    public void testMatch2() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/some/{test0}/some/{test1}/.*");
        
        Map<String, String> values = new HashMap<String, String>();
        
        assertTrue(matcher.match(values, "/some/val0/some/val1/buaaa", expr));
        assertEquals(5, values.size());
        assertEquals("val0", values.get("test0"));
        assertEquals("val1", values.get("test1"));
        assertEquals("val0", values.get("1"));
        assertEquals("val1", values.get("2"));
        
        assertEquals("/some/val0/some/val1/buaaa", values.get("0"));
    }
