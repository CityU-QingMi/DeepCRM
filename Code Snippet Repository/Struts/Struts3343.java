    public void testSMDReturnObject() throws Exception {
        // request
        setRequestContent("smd-10.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest2 action = new SMDActionTest2();

        this.invocation.setAction(action);

        // can't be invoked
        interceptor.intercept(this.invocation);
        assertFalse(this.invocation.isInvoked());

        String json = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("smd-12.txt"));
        assertEquals(normalizedExpected, normalizedActual);

        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
