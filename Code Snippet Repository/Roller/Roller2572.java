    protected void setUp() throws Exception {
        // setup planet
        TestUtils.setupWeblogger();

        log.info("ENTERED");
        
        testPlanet = TestUtils.setupPlanet("entryFuncTestPlanet");
        testGroup1 = TestUtils.setupGroup(testPlanet, "entryFuncTestGroup");
        testSub1 = TestUtils.setupSubscription("entryFuncTestSub1");
        testSub2 = TestUtils.setupSubscription("entryFuncTestSub2");
        testEntry1 = TestUtils.setupEntry(testSub1, "entryFuncTestEntry1");
        testEntry2 = TestUtils.setupEntry(testSub2, "entryFuncTestEntry2");
        testEntry3 = TestUtils.setupEntry(testSub2, "entryFuncTestEntry3");
        
        // now associate both subscriptions with the test group
        testGroup1.getSubscriptions().add(testSub1);
        testSub1.getGroups().add(testGroup1);
        
        testGroup1.getSubscriptions().add(testSub2);
        testSub2.getGroups().add(testGroup1);
        
        WebloggerFactory.getWeblogger().getPlanetManager().saveGroup(testGroup1);
        WebloggerFactory.getWeblogger().flush();
        
        log.info("EXITED");
    }
