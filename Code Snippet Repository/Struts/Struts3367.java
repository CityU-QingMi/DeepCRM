    public void testWrapPrefix() throws Exception {
        JSONResult result = new JSONResult();
        result.setWrapPrefix("_prefix_");
        TestAction2 action = new TestAction2();
        stack.push(action);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String out = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(out, true);
        String normalizedExpected = "_prefix_{\"name\":\"name\"}";
        assertEquals(normalizedExpected, normalizedActual);
    }
