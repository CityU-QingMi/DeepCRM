    public void testSMDDefaultAnnotations() throws Exception {
        JSONResult result = new JSONResult();
        result.setEnableSMD(true);
        SMDActionTest2 action = new SMDActionTest2();
        stack.push(action);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String smd = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(smd, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("smd-2.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
