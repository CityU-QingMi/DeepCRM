    public void testActionGetText() throws Exception {
        ModelDrivenAction2 action = new ModelDrivenAction2();
        container.inject(action);

        TestBean2 bean = (TestBean2) action.getModel();
        Bar bar = new Bar();
        bean.setBarObj(bar);

        Mock mockActionInvocation = new Mock(ActionInvocation.class);
        mockActionInvocation.expectAndReturn("getAction", action);
        ActionContext.getContext().setActionInvocation((ActionInvocation) mockActionInvocation.proxy());
        ActionContext.getContext().getValueStack().push(action);
        ActionContext.getContext().getValueStack().push(action.getModel());

        String message = action.getText("barObj.title");
        assertEquals("Title:", message);
    }
