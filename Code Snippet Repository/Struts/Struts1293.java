    public void testModelOnSession() throws Exception {
        inter.setScope("session");
        inter.setName("king");

        User user = new User();
        user.setName("King George");
        Map session = new HashMap();
        ActionContext.getContext().setSession(session);
        ActionContext.getContext().getSession().put("king", user);

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
        assertEquals("king", action.getScopeKey());

        Object model = ActionContext.getContext().getSession().get(action.getScopeKey());
        assertNotNull(model);
        assertTrue("Model should be an User object", model instanceof User);
        assertEquals("King George", ((User) model).getName());
    }
