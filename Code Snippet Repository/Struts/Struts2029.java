    public void testIngoreContextParamsFalse() throws Exception {
        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setNamespace("");
        tag.setName("testActionTagAction");
        tag.setExecuteResult(false);
        tag.setIgnoreContextParams(false);

        Map<String, String[]> params = new HashMap<>();
        params.put("user", new String[]{"Santa Claus"});
        ActionContext.getContext().setParameters(HttpParameters.create(params).build());

        tag.doStartTag();

        // tag clear components on doEndTag, so we need to get it here
        ActionComponent component = (ActionComponent) tag.getComponent();

        tag.doEndTag();

        // check parameters, there should be one
        ActionInvocation ai = component.getProxy().getInvocation();
        ActionContext ac = ai.getInvocationContext();
        assertEquals(1, ac.getParameters().keySet().size());
    }
