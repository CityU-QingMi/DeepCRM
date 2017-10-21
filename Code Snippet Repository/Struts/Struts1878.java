    public void testNoContentMultipartRequest() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();

        req.setCharacterEncoding("text/html");
        req.setMethod("post");
        req.addHeader("Content-type", "multipart/form-data");
        req.setContent(null); // there is no content

        MyFileupAction action = container.inject(MyFileupAction.class);
        MockActionInvocation mai = new MockActionInvocation();
        mai.setAction(action);
        mai.setResultCode("success");
        mai.setInvocationContext(ActionContext.getContext());

        ActionContext.getContext().setParameters(HttpParameters.create().build());
        ActionContext.getContext().put(ServletActionContext.HTTP_REQUEST, createMultipartRequest(req, 2000));

        interceptor.intercept(mai);

        assertTrue(action.hasErrors());
    }
