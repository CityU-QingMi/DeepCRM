    public void testSimpleTest() throws Exception {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { RunTest.class });
        testng.setOutputDirectory("target/surefire-reports");
        testng.addListener(tla);
        try {
            testng.run();
            assertEquals(1, tla.getPassedTests().size());
            assertEquals(0, tla.getFailedTests().size());
            assertTrue(RunTest.ran);
            assertNotNull(RunTest.mgr);
        } finally {
            RunTest.mgr = null;
        }
    }
