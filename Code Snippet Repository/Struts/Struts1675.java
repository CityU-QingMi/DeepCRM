    public void testRethrowException() throws Exception {
        request.setupGetServletPath(TestConfigurationProvider.TEST_NAMESPACE + "/"
                + "foo.action" );
        ActionComponent ac = new ActionComponent(stack, request, response) ;
        container.inject(ac);
        ac.setNamespace(TestConfigurationProvider.TEST_NAMESPACE);
        ac.setName(TestConfigurationProvider.TEST_ACTION_NAME + "!executeThrowsException");
        ac.setRethrowException(true);
        boolean exceptionCaught = false;
        try {
            ac.executeAction();
        }
        catch (Exception e) {
            if (e instanceof StrutsException)
                exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }
