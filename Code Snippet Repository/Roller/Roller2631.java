    public void testAutoPingLookups() throws Exception {
        
        AutoPingManager mgr = WebloggerFactory.getWeblogger().getAutopingManager();
        PingTargetManager ptmgr = WebloggerFactory.getWeblogger().getPingTargetManager();
        AutoPing autoPing = null;
        
        // create autoPing target to use for tests
        PingTarget pingTarget = TestUtils.setupPingTarget("fooPing", "http://foo/null");
        TestUtils.endSession(true);
        
        // create autoPing
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        pingTarget = ptmgr.getPingTarget(pingTarget.getId());
        autoPing = new AutoPing(null, pingTarget, testWeblog);
        mgr.saveAutoPing(autoPing);
        String id = autoPing.getId();
        TestUtils.endSession(true);
        
        // lookup by id
        autoPing = null;
        autoPing = mgr.getAutoPing(id);
        assertNotNull(autoPing);
        assertEquals(pingTarget, autoPing.getPingTarget());
        
        // lookup by ping target
        pingTarget = ptmgr.getPingTarget(pingTarget.getId());
        List autoPings = mgr.getAutoPingsByTarget(pingTarget);
        assertNotNull(autoPings);
        assertEquals(1, autoPings.size());
        
        // lookup by weblog
        autoPings = null;
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        autoPings = mgr.getAutoPingsByWebsite(testWeblog);
        assertNotNull(autoPing);
        assertEquals(1, autoPings.size());
        
        // delete autoPing
        autoPing = mgr.getAutoPing(autoPing.getId());
        mgr.removeAutoPing(autoPing);
        TestUtils.endSession(true);
        
        // teardown test ping target
        TestUtils.teardownPingTarget(pingTarget.getId());
        TestUtils.endSession(true);
    }
