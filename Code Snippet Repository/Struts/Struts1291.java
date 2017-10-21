    public void testScopedModelDrivenAction() throws Exception {
        inter.setScope("request");

        ScopedModelDriven action = new MyUserScopedModelDrivenAction();
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
        assertEquals("com.opensymphony.xwork2.test.User", action.getScopeKey());

        Object model = ActionContext.getContext().get(action.getScopeKey());
        assertNotNull(model);
        assertTrue("Model should be an User object", model instanceof User);
    }
