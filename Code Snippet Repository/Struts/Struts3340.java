    public void testSMDNoMethod() throws Exception {
        // request
        setRequestContent("smd-4.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest1 action = new SMDActionTest1();

        this.invocation.setAction(action);

        // SMD was enabled so invocation must happen

        interceptor.intercept(this.invocation);

        String json = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("smd-13.txt"));
        assertEquals(normalizedExpected, normalizedActual);

        assertFalse(this.invocation.isInvoked());
    }
