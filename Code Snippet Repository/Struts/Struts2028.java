     public void testExecuteButResetReturnSameInvocation() throws Exception {
        Mock mockActionInv = new Mock(ActionInvocation.class);
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction");
        tag.setExecuteResult(true);
        ActionContext.getContext().setActionInvocation((ActionInvocation) mockActionInv.proxy());

        ActionInvocation oldInvocation = ActionContext.getContext().getActionInvocation();
        assertNotNull(oldInvocation);

        tag.doStartTag();

        // tag clear components on doEndTag
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();
        assertTrue(oldInvocation == ActionContext.getContext().getActionInvocation());
    }
