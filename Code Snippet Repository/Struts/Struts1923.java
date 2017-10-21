    public void testHeaderValuesAreNotParsedWhenParseIsFalse() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("headers.foo", "${bar}");
        params.put("headers.baz", "baz");

        Map<String, String> values = new HashMap<String, String>();
        values.put("bar", "abc");
        ActionContext.getContext().getValueStack().push(values);

        reflectionProvider.setProperties(params, result);

        responseMock.expect("addHeader", C.args(C.eq("foo"), C.eq("${bar}")));
        responseMock.expect("addHeader", C.args(C.eq("baz"), C.eq("baz")));
        result.setParse(false);
        result.execute(invocation);
        responseMock.verify();
    }
