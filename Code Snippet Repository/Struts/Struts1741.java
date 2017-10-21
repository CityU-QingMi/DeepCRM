    public void testConfigurationManager() {
    	Dispatcher du;
    	final InternalConfigurationManager configurationManager = new InternalConfigurationManager(Container.DEFAULT_NAME);
    	try {
    		du = new MockDispatcher(new MockServletContext(), new HashMap<String, String>(), configurationManager);
    		du.init();
            Dispatcher.setInstance(du);
            
            assertFalse(configurationManager.destroyConfiguration);
            
            du.cleanup();
            
            assertTrue(configurationManager.destroyConfiguration);
            
    	}
    	finally {
    		Dispatcher.setInstance(null);
    	}
    }
