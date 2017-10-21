    private void tryBadJSON(String fileName) throws Exception {
        // request
        setRequestContent(fileName);
        this.request.addHeader("Content-Type", "application/json; charset=UTF-8");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest1 action = new SMDActionTest1();

        this.invocation.setAction(action);

        // JSON is not well formed, throw exception
        try {
            interceptor.intercept(this.invocation);
            fail("Should have thrown an exception");
        } catch (JSONException e) {
            // I can't get JUnit to ignore the exception
            // @Test(expected = JSONException.class)
        }
    }
