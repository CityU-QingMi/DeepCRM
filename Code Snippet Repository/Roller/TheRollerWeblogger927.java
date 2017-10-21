    protected void setUp() throws Exception {
        // setup planet
        TestUtils.setupWeblogger();

        testPlanet = TestUtils.setupPlanet("subFuncTest");
        testGroup1 = TestUtils.setupGroup(testPlanet, "subFuncTest1");
        testGroup2 = TestUtils.setupGroup(testPlanet, "subFuncTest2");
        testSub1 = TestUtils.setupSubscription("subFuncTest1");
        testSub2 = TestUtils.setupSubscription("subFuncTest2");
    }
