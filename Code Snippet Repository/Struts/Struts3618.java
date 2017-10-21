    public void testHandleResultOK() throws IOException {

        String obj = "mystring";
        ContentTypeHandler handler = new AbstractContentTypeHandler() {
            public void toObject(ActionInvocation invocation, Reader in, Object target) {}
            public String fromObject(ActionInvocation invocation, Object obj, String resultCode, Writer stream) throws IOException {
                stream.write(obj.toString());
                return resultCode;
            }
            public String getContentType() { return "foo"; }
            public String getExtension() { return "foo"; }
        };
        mgr.handlersByExtension.put("xml", handler);
        mgr.setDefaultExtension("xml");
        ActionConfig actionConfig = new ActionConfig.Builder("", "", "").build();
        MockActionProxy proxy = new MockActionProxy();
        proxy.setConfig(actionConfig);
        invocation.setProxy(proxy);
        mgr.handleResult(invocation, new DefaultHttpHeaders().withStatus(SC_OK), obj);

        assertEquals(obj.getBytes().length, mockResponse.getContentLength());
    }
