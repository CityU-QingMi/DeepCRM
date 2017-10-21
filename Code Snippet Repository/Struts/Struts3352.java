    @SuppressWarnings("")
    public void testHierarchy() throws Exception {
        JSONResult result = new JSONResult();
        result.setIgnoreHierarchy(false);

        TestAction3 action = new TestAction3();
        stack.push(action);
        this.invocation.setAction(action);
        result.execute(this.invocation);

        String json = response.getContentAsString();
        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("json-4.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
