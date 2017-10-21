    public void testParserWithBadXML2() {
        InputStream is = ClassLoaderUtil.getResourceAsStream(testFileNameFail, this.getClass());

        boolean pass = false;
        try {
            parser.parseActionValidatorConfigs((ValidatorFactory) mockValidatorFactory.proxy(), is, testFileNameFail);
        } catch (XWorkException ex) {
            assertTrue("Wrong line number: " + ex.getLocation(), 8 == ex.getLocation().getLineNumber());
            pass = true;
        }
        assertTrue("Validation file should have thrown exception", pass);
    }
