    public void testPingTargetCRUD() throws Exception {
        
        PingTargetManager mgr = WebloggerFactory.getWeblogger().getPingTargetManager();
        PingTarget ping = null;
        
        // create common ping
        mgr.savePingTarget(testCommonPing);
        String commonId = testCommonPing.getId();
        TestUtils.endSession(true);
        
        // make sure common ping was stored
        ping = null;
        ping = mgr.getPingTarget(commonId);
        assertNotNull(ping);
        assertEquals(testCommonPing.getPingUrl(), ping.getPingUrl());
        
        // update common ping
        ping = null;
        ping = mgr.getPingTarget(commonId);
        ping.setName("testtestCommon");
        mgr.savePingTarget(ping);
        TestUtils.endSession(true);
        
        // make sure common ping was updated
        ping = null;
        ping = mgr.getPingTarget(commonId);
        assertNotNull(ping);
        assertEquals("testtestCommon", ping.getName());
        
        // delete common ping
        ping = null;
        ping = mgr.getPingTarget(commonId);
        mgr.removePingTarget(ping);
        TestUtils.endSession(true);
        
        // make sure common ping was deleted
        ping = null;
        ping = mgr.getPingTarget(commonId);
        assertNull(ping);
    }
