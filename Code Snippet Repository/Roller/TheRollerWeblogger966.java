    public void testAutoPingCRUD() throws Exception {
        
        AutoPingManager mgr = WebloggerFactory.getWeblogger().getAutopingManager();
        PingTargetManager pingMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
        AutoPing autoPing = null;
        
        // create ping target to use for tests
        PingTarget pingTarget = TestUtils.setupPingTarget("fooPing", "http://foo/null");
        PingTarget pingTarget2 = TestUtils.setupPingTarget("blahPing", "http://blah/null");
        TestUtils.endSession(true);
        
        // create autoPing
        autoPing = new AutoPing(null, pingTarget, testWeblog);
        mgr.saveAutoPing(autoPing);
        String id = autoPing.getId();
        TestUtils.endSession(true);
        
        // make sure autoPing was stored
        autoPing = null;
        autoPing = mgr.getAutoPing(id);
        assertNotNull(autoPing);
        assertEquals(pingTarget, autoPing.getPingTarget());
        
        // update autoPing
        autoPing.setPingTarget(pingMgr.getPingTarget(pingTarget2.getId()));
        mgr.saveAutoPing(autoPing);
        TestUtils.endSession(true);
        
        // make sure autoPing was updated
        autoPing = null;
        autoPing = mgr.getAutoPing(id);
        assertNotNull(autoPing);
        assertEquals(pingTarget2, autoPing.getPingTarget());
        
        // delete autoPing
        mgr.removeAutoPing(autoPing);
        TestUtils.endSession(true);
        
        // make sure common autoPing was deleted
        autoPing = null;
        autoPing = mgr.getAutoPing(id);
        assertNull(autoPing);
        
        // teardown test ping target
        TestUtils.teardownPingTarget(pingTarget.getId());
        TestUtils.teardownPingTarget(pingTarget2.getId());
        TestUtils.endSession(true);
    }
