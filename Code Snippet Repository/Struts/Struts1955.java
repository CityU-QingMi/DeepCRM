    protected void setUp() throws Exception {
        super.setUp();
        response = new MockHttpServletResponse();

        result = new StreamResult();
        result.setContentLength("${contentLength}");
        stack = ActionContext.getContext().getValueStack();

        MyImageAction action = new MyImageAction();
        contentLength = (int) action.getContentLength();

        mai = new com.opensymphony.xwork2.mock.MockActionInvocation();
        mai.setAction(action);
        mai.setStack(stack);
        mai.setInvocationContext(ActionContext.getContext());
        stack.push(action);

        ActionContext.getContext().put(ServletActionContext.HTTP_RESPONSE, response);
    }
