    public void testPrefix() throws Exception {
        JSONResult result = new JSONResult();
        result.setExcludeNullProperties(true);
        result.setPrefix(true);
        TestAction action = new TestAction();
        stack.push(action);
        action.setFoo("fool");

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String smd = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(smd, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("prefix-1.txt"));
        assertEquals(normalizedExpected, normalizedActual);
    }
