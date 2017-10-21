    public void testTranslateVariablesRecursive() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new HashMap<String, Object>() {{ put("foo", "${1+1}"); put("bar", "${${1+2}}"); }});

        Object s = TextParseUtil.translateVariables('$', "foo: ${foo}", stack, String.class, null, 2);
        assertEquals("foo: 2", s);

        s = TextParseUtil.translateVariables('$', "foo: ${bar}", stack, String.class, null, 1);
        assertEquals("foo: ${${1+2}}", s);
    }
