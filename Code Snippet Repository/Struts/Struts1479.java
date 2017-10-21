    public void testTranslateVariablesOpenChar() {
        // just a quick test to see if the open char works
        // most test are done the methods above
        ValueStack stack = ActionContext.getContext().getValueStack();

        Object s = TextParseUtil.translateVariables('$', "foo: ${{1, 2, 3}}, bar: ${1}", stack);
        assertEquals("foo: [1, 2, 3], bar: 1", s);

        Object s2 = TextParseUtil.translateVariables('#', "foo: #{{1, 2, 3}}, bar: #{1}", stack);
        assertEquals("foo: [1, 2, 3], bar: 1", s2);
    }
