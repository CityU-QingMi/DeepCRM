    public void testTranslateVariables() {
        ValueStack stack = ActionContext.getContext().getValueStack();

        Object s = TextParseUtil.translateVariables("foo: ${{1, 2, 3}}, bar: %{1}", stack);
        assertEquals("foo: [1, 2, 3], bar: 1", s);

        s = TextParseUtil.translateVariables("foo: %{{1, 2, 3}}, bar: %{1}", stack);
        assertEquals("foo: [1, 2, 3], bar: 1", s);

        s = TextParseUtil.translateVariables("foo: %{{1, 2, 3}}, bar: %{1}", stack);
        assertEquals("foo: [1, 2, 3], bar: 1", s);

        s = TextParseUtil.translateVariables("foo: ${#{1 : 2, 3 : 4}}, bar: ${1}", stack);
        assertEquals("foo: {1=2, 3=4}, bar: 1", s);

        s = TextParseUtil.translateVariables("foo: %{#{1 : 2, 3 : 4}}, bar: %{1}", stack);
        assertEquals("foo: {1=2, 3=4}, bar: 1", s);

        s = TextParseUtil.translateVariables("foo: 1}", stack);
        assertEquals("foo: 1}", s);

        s = TextParseUtil.translateVariables("foo: {1}", stack);
        assertEquals("foo: {1}", s);

        s = TextParseUtil.translateVariables("foo: ${1", stack);
        assertEquals("foo: ${1", s);

        s = TextParseUtil.translateVariables("foo: %{1", stack);
        assertEquals("foo: %{1", s);

        s =  TextParseUtil.translateVariables('$', "${{1, 2, 3}}", stack, Object.class);
        assertNotNull(s);
        assertTrue("List not returned when parsing a 'pure' list", s instanceof List);
        assertEquals(((List)s).size(), 3);

        s = TextParseUtil.translateVariables('$', "${#{'key1':'value1','key2':'value2','key3':'value3'}}", stack, Object.class);
        assertNotNull(s);
        assertTrue("Map not returned when parsing a 'pure' map", s instanceof Map);
        assertEquals(((Map)s).size(), 3);

        s =  TextParseUtil.translateVariables('$', "${1} two ${3}", stack, Object.class);
        assertEquals("1 two 3", s);

        s = TextParseUtil.translateVariables('$', "count must be between ${123} and ${456}, current value is ${98765}.", stack, Object.class);
        assertEquals("count must be between 123 and 456, current value is 98765.", s);
    }
