    public static junit.framework.Test suite() {

        junit.framework.TestSuite newSuite = new junit.framework.TestSuite();

        newSuite.addTest(new TestSchemaParse("testSanityCheck"));
        newSuite.addTest(new TestSchemaParse("testTwoPartKeywords"));
        newSuite.addTest(new TestSchemaParse("testThreePartKeywords"));
        newSuite.addTest(new TestSchemaParse("testThreePartNames"));
        newSuite.addTest(new TestSchemaParse("testBasicQueries"));
        newSuite.addTest(new TestSchemaParse("test2pTables"));
        newSuite.addTest(new TestSchemaParse("test2pViews"));
        newSuite.addTest(new TestSchemaParse("test2pSequences"));
        newSuite.addTest(new TestSchemaParse("test2pIndexes"));
        newSuite.addTest(new TestSchemaParse("test2pAliases"));
        newSuite.addTest(new TestSchemaParse("test2pConstraints"));
        newSuite.addTest(new TestSchemaParse("test2pTriggers"));

        return newSuite;
    }
