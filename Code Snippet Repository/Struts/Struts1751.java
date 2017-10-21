    public void testDispatcherListener() throws Exception {
    	
    	final DispatcherListenerState state = new DispatcherListenerState();
    	
    	Dispatcher.addDispatcherListener(new DispatcherListener() {
			public void dispatcherDestroyed(Dispatcher du) {
				state.isDestroyed = true;
			}
			public void dispatcherInitialized(Dispatcher du) {
				state.isInitialized = true;
			}
    	});
    	
    	
    	assertFalse(state.isDestroyed);
    	assertFalse(state.isInitialized);
    	
        Dispatcher du = initDispatcher(new HashMap<String, String>() );
    	
    	assertTrue(state.isInitialized);
    	
    	du.cleanup();

    	assertTrue(state.isDestroyed);
    }
