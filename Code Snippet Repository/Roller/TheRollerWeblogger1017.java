    public static Test suite() {

        TestSuite suite = new TestSuite();
	
        // comment plugins
        suite.addTestSuite(CommentValidatorTest.class);
        
        // custom planet fetcher
        suite.addTestSuite(WebloggerRomeFeedFetcherTest.class);
        
        return suite;
    }
