    public void testSMDMethodWithoutAnnotations() throws Exception {
        // request
        setRequestContent("smd-9.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest1 action = new SMDActionTest1();

        this.invocation.setAction(action);

        // SMD was enabled so invocation must happen
        try {
            interceptor.intercept(this.invocation);
            assertTrue("Exception was expected here!", true);
        } catch (Exception e) {
            // ok
        }
        assertFalse(this.invocation.isInvoked());
    }
