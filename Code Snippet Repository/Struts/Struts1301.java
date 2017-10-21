    public void testFewParametersParse() throws Exception {
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy map = new MockActionProxy();
        ActionConfig ac = new ActionConfig.Builder("", "", "")
                .addParam("top.age", "${top.myAge}")
                .addParam("top.email", "${top.myEmail}")
                .build();
        map.setConfig(ac);
        mai.setProxy(map);
        mai.setAction(new SimpleFooAction());

        User user = new User();
        ActionContext.getContext().getValueStack().push(user);
        int before = ActionContext.getContext().getValueStack().size();
        interceptor.setParse("true");
        interceptor.intercept(mai);

        assertEquals(before, ActionContext.getContext().getValueStack().size());
        assertEquals(user.getMyAge(), user.age);
        assertEquals(user.getMyEmail(), user.email);
    }
