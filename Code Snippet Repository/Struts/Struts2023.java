    public void testActionMethodWithExecuteResult() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction!input");
        tag.setExecuteResult(true);
        ((DefaultActionMapper)container.getInstance(ActionMapper.class)).setAllowDynamicMethodCalls("true");

        tag.doStartTag();

        // tag clear components on doEndTag
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        TestActionTagResult result = (TestActionTagResult) component.getProxy().getInvocation().getResult();

        assertTrue(stack.getContext().containsKey(ServletActionContext.PAGE_CONTEXT));
        assertTrue(stack.getContext().get(ServletActionContext.PAGE_CONTEXT)instanceof PageContext);
        assertTrue(result.isExecuted());
    }
