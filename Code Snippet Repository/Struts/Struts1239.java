    public void testModelDrivenUpdatedAndGetsPushedOntoStack() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        action = new ModelDrivenAction();
        mockActionInvocation.expectAndReturn("getAction", action);
        mockActionInvocation.matchAndReturn("getStack", stack);
        mockActionInvocation.expectAndReturn("invoke", "foo");
        mockActionInvocation.expect("addPreResultListener", new ConstraintMatcher() {

            public boolean matches(Object[] objects) {
                preResultListener = (PreResultListener) objects[0];
                return true;
            }

            public Object[] getConstraints() {
                return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        modelDrivenInterceptor.setRefreshModelBeforeResult(true);

        modelDrivenInterceptor.intercept((ActionInvocation) mockActionInvocation.proxy());
        assertNotNull(preResultListener);
        model = "this is my model";
        preResultListener.beforeResult((ActionInvocation) mockActionInvocation.proxy(), "success");

        Object topOfStack = stack.pop();
        assertEquals("our model should be on the top of the stack", model, topOfStack);
        assertEquals(1, stack.getRoot().size());
    }
