    public void testManagementOfTriggerListeners() throws Exception {
        
    	TriggerListener tl1 = new TestTriggerListener("tl1");
    	TriggerListener tl2 = new TestTriggerListener("tl2");
        
        ListenerManagerImpl manager = new ListenerManagerImpl();

        // test adding listener without matcher
        manager.addTriggerListener(tl1);
        assertEquals("Unexpected size of listener list", 1, manager.getTriggerListeners().size());

        // test adding listener with matcher
        manager.addTriggerListener(tl2, triggerGroupEquals("foo"));
        assertEquals("Unexpected size of listener list", 2, manager.getTriggerListeners().size());

        // test removing a listener
        manager.removeTriggerListener("tl1");
        assertEquals("Unexpected size of listener list", 1, manager.getTriggerListeners().size());
        
        // test adding a matcher
        manager.addTriggerListenerMatcher("tl2", NameMatcher.<TriggerKey>nameContains("foo"));
        assertEquals("Unexpected size of listener's matcher list", 2, manager.getTriggerListenerMatchers("tl2").size());
        
        // Test ordering of registration is preserved.
        final int numListenersToTestOrderOf = 15;
        manager = new ListenerManagerImpl();
        TriggerListener[] lstners = new TriggerListener[numListenersToTestOrderOf];
        for(int i=0; i < numListenersToTestOrderOf; i++) {
        	// use random name, to help test that order isn't based on naming or coincidental hashing
        	lstners[i] = new TestTriggerListener(UUID.randomUUID().toString());
        	manager.addTriggerListener(lstners[i]);
        }
        List<TriggerListener> mls = manager.getTriggerListeners();
        int i = 0;
        for(TriggerListener lsnr: mls) {
        	assertSame("Unexpected order of listeners", lstners[i], lsnr);
        	i++;
        }
    }
