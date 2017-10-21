    public void testIncludeProperties() throws Exception {
        JSONResult result = new JSONResult();
        result.setIncludeProperties("foo");
        TestAction action = new TestAction();
        stack.push(action);
        action.setFoo("fooValue");
        action.setBean(new Bean());
        this.invocation.setAction(action);
        result.execute(this.invocation);

        String json = response.getContentAsString();
        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("json-9.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
