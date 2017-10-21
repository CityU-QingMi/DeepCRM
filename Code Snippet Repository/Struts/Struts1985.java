    public void testWW_3747_2() {
        RegexPatternMatcherExpression expr = matcher.compilePattern("/event/modify/{action}/{eventId:[0-9]+}");

        Map<String, String> values = new HashMap<String, String>();

        assertTrue(matcher.match(values, "/event/modify/delete/1234", expr));
        assertEquals(5, values.size());
        assertEquals("delete", values.get("action"));
        assertEquals("1234", values.get("eventId"));

        assertEquals("/event/modify/delete/1234", values.get("0"));
    }
