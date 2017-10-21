    public void testManagementOfJobListeners() throws Exception {
        
        JobListener tl1 = new TestJobListener("tl1");
        JobListener tl2 = new TestJobListener("tl2");
        
        ListenerManagerImpl manager = new ListenerManagerImpl();

        // test adding listener without matcher
        manager.addJobListener(tl1);
        assertEquals("Unexpected size of listener list", 1, manager.getJobListeners().size());

        // test adding listener with matcher
        manager.addJobListener(tl2, jobGroupEquals("foo"));
        assertEquals("Unexpected size of listener list", 2, manager.getJobListeners().size());

        // test removing a listener
        manager.removeJobListener("tl1");
        assertEquals("Unexpected size of listener list", 1, manager.getJobListeners().size());
        
        // test adding a matcher
        manager.addJobListenerMatcher("tl2", jobNameContains("foo"));
        assertEquals("Unexpected size of listener's matcher list", 2, manager.getJobListenerMatchers("tl2").size());
           
        // Test ordering of registration is preserved.
        final int numListenersToTestOrderOf = 15;
        manager = new ListenerManagerImpl();
        JobListener[] lstners = new JobListener[numListenersToTestOrderOf];
        for(int i=0; i < numListenersToTestOrderOf; i++) {
        	// use random name, to help test that order isn't based on naming or coincidental hashing
        	lstners[i] = new TestJobListener(UUID.randomUUID().toString());
        	manager.addJobListener(lstners[i]);
        }
        List<JobListener> mls = manager.getJobListeners();
        int i = 0;
        for(JobListener lsnr: mls) {
        	assertSame("Unexpected order of listeners", lstners[i], lsnr);
        	i++;
        }        
    }
