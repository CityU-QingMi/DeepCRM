    @SuppressWarnings( { "", "" })
    public void testReadEmpty() throws Exception {
        // request
        setRequestContent("json-6.txt");
        this.request.addHeader("Content-Type", "application/json");

        // interceptor
        JSONInterceptor interceptor = new JSONInterceptor();
        TestAction action = new TestAction();

        this.invocation.setAction(action);

        interceptor.intercept(this.invocation);
    }
