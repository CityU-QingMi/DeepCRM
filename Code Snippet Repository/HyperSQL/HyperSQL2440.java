    public static Test suite()
    throws IOException, ServerAcl.AclFormatException {

        TestSuite newSuite = new TestSuite();

        newSuite.addTest(new TestAcl("testDefaultWithNames"));
        newSuite.addTest(new TestAcl("testDefaultWithIPs"));
        newSuite.addTest(new TestAcl("testDenyAllWithNames"));
        newSuite.addTest(new TestAcl("testDenyAllWithIPs"));
        newSuite.addTest(new TestAcl("testLocalhostOnlyWithNames"));
        newSuite.addTest(new TestAcl("testLocalhostOnlyWithIPs"));
        newSuite.addTest(new TestAcl("testNoLocalhostOnlyWithNames"));
        newSuite.addTest(new TestAcl("testNoLocalhostOnlyWithIPs"));
        newSuite.addTest(new TestAcl("testLocalNetOnlyWithNames"));
        newSuite.addTest(new TestAcl("testLocalNetOnlyWithIPs"));
        newSuite.addTest(new TestAcl("testNoLocalNetOnlyWithNames"));
        newSuite.addTest(new TestAcl("testNoLocalNetOnlyWithIPs"));

        return newSuite;
    }
