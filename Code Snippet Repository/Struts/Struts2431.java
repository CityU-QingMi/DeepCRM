    public void testFieldMatchAction() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "fieldMatchAction", null, null);
        FieldMatchAction action = (FieldMatchAction) baseActionProxy.getAction();
        action.setPassword("pass1");
        action.setConfirmPassword("pass2");
        action.setEmail("test1@mail.org");
        action.setConfirmEmail("test2@mail.org");
        baseActionProxy.execute();

        Collection<String> actionErrors = ((ValidationAware) baseActionProxy.getAction()).getActionErrors();
        System.out.println(actionErrors);

        assertNotNull(actionErrors);
        assertEquals(2, actionErrors.size());
    }
