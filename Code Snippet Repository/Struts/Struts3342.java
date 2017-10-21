    public void testSMDPrimitivesNoResult() throws Exception {
        // request
        setRequestContent("smd-6.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest1 action = new SMDActionTest1();

        this.invocation.setAction(action);

        // can't be invoked
        interceptor.intercept(this.invocation);
        assertFalse(this.invocation.isInvoked());

        // asert values were passed properly
        assertEquals("string", action.getStringParam());
        assertEquals(1, action.getIntParam());
        assertEquals(true, action.isBooleanParam());
        assertEquals('c', action.getCharParam());
        assertEquals(2, action.getLongParam());
        assertEquals(new Float(3.3), action.getFloatParam());
        assertEquals(4.4, action.getDoubleParam());
        assertEquals(5, action.getShortParam());
        assertEquals(6, action.getByteParam());

        String json = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("smd-11.txt"));
        assertEquals(normalizedExpected, normalizedActual);

        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
