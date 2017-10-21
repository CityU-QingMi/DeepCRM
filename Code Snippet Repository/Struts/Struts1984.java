    public void testWW_3747() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/{type}/{author:.+}/list");

        Map<String, String> values = new HashMap<String, String>();

        assertTrue(matcher.match(values, "/philosophy/AynRand/list", expr));
        assertEquals(5, values.size());
        assertEquals("philosophy", values.get("type"));
        assertEquals("AynRand", values.get("author"));

        assertEquals("/philosophy/AynRand/list", values.get("0"));
    }
