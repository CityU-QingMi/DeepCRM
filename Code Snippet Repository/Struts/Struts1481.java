    public void testTranslateVariablesWithNull() {
        // given
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new HashMap<String, Object>() {{ put("foo", null); }});

        TextParseUtil.ParsedValueEvaluator evaluator = new TextParseUtil.ParsedValueEvaluator() {
            public Object evaluate(String parsedValue) {
                return parsedValue;
            }
        };

        // when
        Object s = TextParseUtil.translateVariables('$', "foo: ${foo}", stack, String.class, evaluator, 2);

        // then
        assertEquals("foo: ", s);
    }
