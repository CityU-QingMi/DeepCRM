    public static Test suite() {

        TestSuite suite = new TestSuite();
	
        // TODO: add a test for PlanetConfig
        
        suite.addTestSuite(PropertiesTest.class);
        
        // planets
        suite.addTestSuite(PlanetBasicTests.class);
        suite.addTestSuite(PlanetFunctionalTests.class);
        
        // groups
        suite.addTestSuite(GroupBasicTests.class);
        suite.addTestSuite(GroupFunctionalTests.class);
        
        // subscriptions
        suite.addTestSuite(SubscriptionBasicTests.class);
        suite.addTestSuite(SubscriptionFunctionalTests.class);
        
        // entries
        suite.addTestSuite(EntryBasicTests.class);
        suite.addTestSuite(EntryFunctionalTests.class);
        
        // fetching
        suite.addTestSuite(RomeFeedFetcherTest.class);
        
        // updating
        suite.addTestSuite(SingleThreadedFeedUpdaterTest.class);
        
        return suite;
    }
