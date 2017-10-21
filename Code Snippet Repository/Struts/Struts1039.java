    @Test
    public void testThorwExceptionOnNotAllowedMethod() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-allowed-methods.xml";
        loadConfigurationProviders(new XmlConfigurationProvider(filename));
        DefaultActionProxy dap = new DefaultActionProxy(new MockActionInvocation(), "strict", "Default", "notAllowed", true, true);
        container.inject(dap);

        try {
            dap.prepare();
            fail("Must throw exception!");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Method notAllowed for action Default is not allowed!");
        }
    }
