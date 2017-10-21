    public void testSMDDisabledSMD() throws Exception {
        JSONResult result = new JSONResult();
        SMDActionTest1 action = new SMDActionTest1();
        stack.push(action);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String smd = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(smd, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("smd-8.txt"));
        assertEquals(normalizedExpected, normalizedActual);
    }
