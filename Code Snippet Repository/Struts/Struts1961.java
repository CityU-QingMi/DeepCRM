    protected void setUp() throws Exception {
        super.setUp();
        namespace = "/html";
        result = new VelocityResult();
        stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);
        velocity = new TestVelocityEngine();
        mockActionProxy = new Mock(ActionProxy.class);
        mockActionProxy.expectAndReturn("getNamespace", "/html");

        Mock mockActionInvocation = new Mock(ActionInvocation.class);
        mockActionInvocation.expectAndReturn("getProxy", mockActionProxy.proxy());
        mockActionInvocation.expectAndReturn("getStack", stack);
        actionInvocation = (ActionInvocation) mockActionInvocation.proxy();
    }
