    public void testExcludeNullPropeties() throws Exception {
        JSONResult result = new JSONResult();
        result.setExcludeNullProperties(true);
        TestAction action = new TestAction();
        stack.push(action);
        action.setFoo("fool");

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String smd = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(smd, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("nulls-1.txt"));
        assertEquals(normalizedExpected, normalizedActual);
    }
