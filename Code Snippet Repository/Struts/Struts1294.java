    public void testModelAlreadySetOnAction() throws Exception {
        inter.setScope("request");
        inter.setName("king");

        User user = new User();
        user.setName("King George");

        ScopedModelDriven action = new MyUserScopedModelDrivenAction();
        action.setModel(user);
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy map = new MockActionProxy();
        ActionConfig ac = new ActionConfig.Builder("", "", "").build();
        map.setConfig(ac);
        mai.setAction(action);
        mai.setProxy(map);

        inter.intercept(mai);
        inter.destroy();

        assertNotNull(action.getModel());
        assertNull(action.getScopeKey()); // no scope key as nothing happended
    }
