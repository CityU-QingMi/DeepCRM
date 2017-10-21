    @Test
    public void testNamedLogger()
    {
        jul.clear();
        JavaUtilLog log = new JavaUtilLog("test");
        log.info("Info test");

        jul.assertContainsLine("INFO|test|Info test");

        JavaUtilLog loglong = new JavaUtilLog("test.a.long.name");
        loglong.info("Long test");

        jul.assertContainsLine("INFO|test.a.long.name|Long test");
    }
