    public void testMultipartRequestLocalizedError() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCharacterEncoding("text/html");
        req.setMethod("post");
        req.addHeader("Content-type", "multipart/form-data; boundary=---1234");

        // inspired by the unit tests for jakarta commons fileupload
        String content = ("-----1234\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"deleteme.txt\"\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "Unit test of FileUploadInterceptor" +
                "\r\n" +
                "-----1234--\r\n");
        req.setContent(content.getBytes("US-ASCII"));

        MyFileupAction action = container.inject(MyFileupAction.class);

        MockActionInvocation mai = new MockActionInvocation();
        mai.setAction(action);
        mai.setResultCode("success");
        mai.setInvocationContext(ActionContext.getContext());
        Map<String, Object> param = new HashMap<>();
        ActionContext.getContext().setParameters(HttpParameters.create(param).build());
        // set German locale
        ActionContext.getContext().setLocale(Locale.GERMAN);
        ActionContext.getContext().put(ServletActionContext.HTTP_REQUEST, createMultipartRequest(req, 10));

        interceptor.intercept(mai);

        assertTrue(action.hasActionErrors());

        Collection<String> errors = action.getActionErrors();
        assertEquals(1, errors.size());
        String msg = errors.iterator().next();
        // the error message should contain at least this test
        assertTrue(msg.startsWith("Der Request übertraf die maximal erlaubte Größe"));
    }
