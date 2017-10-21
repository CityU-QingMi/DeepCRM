    public void testTaskLockCRUD() throws Exception {
        
        ThreadManager mgr = WebloggerFactory.getWeblogger().getThreadManager();
        
        // need a test task to play with
        TestTask task = new TestTask();
        task.init();
        
        // try to acquire a lock
        assertTrue("Failed to acquire lease.",mgr.registerLease(task));
        // We don't flush here because registerLease should flush on its own
        TestUtils.endSession(false);
        
        // make sure task is locked
        assertFalse("Acquired lease a second time when we shouldn't have been able to.",mgr.registerLease(task));
        TestUtils.endSession(false);
        
        // try to release a lock
        assertTrue("Release of lease failed.",mgr.unregisterLease(task));
        // We don't flush here because unregisterLease should flush on its own
        TestUtils.endSession(false);

        // Current unregisterLease semantics are idempotent.  Double release should
        // actually succeed.
        assertTrue("Second release failed.", mgr.unregisterLease(task));
        TestUtils.endSession(false);
    }
