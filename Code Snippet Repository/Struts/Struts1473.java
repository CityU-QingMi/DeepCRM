    public void testFindTextInPackage() throws Exception {
        ModelDriven action = new ModelDrivenAction2();

        Mock mockActionInvocation = new Mock(ActionInvocation.class);
        mockActionInvocation.expectAndReturn("getAction", action);
        ActionContext.getContext().setActionInvocation((ActionInvocation) mockActionInvocation.proxy());

        String message = localizedTextProvider.findText(ModelDrivenAction2.class, "package.properties", Locale.getDefault());
        assertEquals("It works!", message);
    }
