    protected void tearDown() throws Exception {
        log.info("ENTERED");
        
        TestUtils.teardownSubscription(testSub1.getId());
        TestUtils.teardownSubscription(testSub2.getId());
        TestUtils.teardownGroup(testGroup1.getId());
        TestUtils.teardownPlanet(testPlanet.getId());
        
        log.info("EXITED");
    }
