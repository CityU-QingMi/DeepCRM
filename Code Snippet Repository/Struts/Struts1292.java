    public void testScopedModelDrivenActionWithSetClassName() throws Exception {
        inter.setScope("request");
        inter.setClassName("com.opensymphony.xwork2.test.Equidae");
        inter.setName("queen");

        ScopedModelDriven action = new MyEquidaeScopedModelDrivenAction();
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy map = new MockActionProxy();
        ActionConfig ac = new ActionConfig.Builder("", "", "").build();
        map.setConfig(ac);
        mai.setAction(action);
        mai.setProxy(map);

        inter.intercept(mai);
        inter.destroy();

        assertNotNull(action.getModel());
        assertNotNull(action.getScopeKey());
        assertEquals("queen", action.getScopeKey());

        Object model = ActionContext.getContext().get(action.getScopeKey());
        assertNotNull(model);
        assertTrue("Model should be an Equidae object", model instanceof Equidae);
    }
