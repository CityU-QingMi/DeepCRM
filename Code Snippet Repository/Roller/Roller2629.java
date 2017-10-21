    public void testPingTargetLookups() throws Exception {
        
        PingTargetManager mgr = WebloggerFactory.getWeblogger().getPingTargetManager();
        PingTarget ping = null;
        
        // create common ping
        mgr.savePingTarget(testCommonPing);
        String commonId = testCommonPing.getId();
        TestUtils.endSession(true);
        
        // lookup by id
        ping = null;
        ping = mgr.getPingTarget(commonId);
        assertNotNull(ping);
        assertEquals(testCommonPing.getName(), ping.getName());
        
        // lookup all common pings
        List commonPings = mgr.getCommonPingTargets();
        assertNotNull(commonPings);
        // correct answer is: 4 pings in config + 1 new one = 5
        assertEquals(5, commonPings.size());
        
        // delete common ping
        ping = null;
        ping = mgr.getPingTarget(commonId);
        mgr.removePingTarget(ping);
        TestUtils.endSession(true);
    }
