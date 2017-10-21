    public void testManagementOfSchedulerListeners() throws Exception {
        
        SchedulerListener tl1 = new TestSchedulerListener();
        SchedulerListener tl2 = new TestSchedulerListener();
        
        ListenerManagerImpl manager = new ListenerManagerImpl();

        // test adding listener without matcher
        manager.addSchedulerListener(tl1);
        assertEquals("Unexpected size of listener list", 1, manager.getSchedulerListeners().size());

        // test adding listener with matcher
        manager.addSchedulerListener(tl2);
        assertEquals("Unexpected size of listener list", 2, manager.getSchedulerListeners().size());

        // test removing a listener
        manager.removeSchedulerListener(tl1);
        assertEquals("Unexpected size of listener list", 1, manager.getSchedulerListeners().size());
        
        
        // Test ordering of registration is preserved.
        final int numListenersToTestOrderOf = 15;
        manager = new ListenerManagerImpl();
        SchedulerListener[] lstners = new SchedulerListener[numListenersToTestOrderOf];
        for(int i=0; i < numListenersToTestOrderOf; i++) {
        	lstners[i] = new TestSchedulerListener();
        	manager.addSchedulerListener(lstners[i]);
        }
        List<SchedulerListener> mls = manager.getSchedulerListeners();
        int i = 0;
        for(SchedulerListener lsnr: mls) {
        	assertSame("Unexpected order of listeners", lstners[i], lsnr);
        	i++;
        } 
    }
