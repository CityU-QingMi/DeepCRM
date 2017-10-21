    public void testParserWithBadXML() {
        InputStream is = ClassLoaderUtil.getResourceAsStream(testFileName4, this.getClass());

        boolean pass = false;
        try {
            parser.parseActionValidatorConfigs((ValidatorFactory) mockValidatorFactory.proxy(), is, testFileName4);
        } catch (XWorkException ex) {
            assertTrue("Wrong line number: " + ex.getLocation(), 13 == ex.getLocation().getLineNumber());
            pass = true;
        }
        assertTrue("Validation file should have thrown exception", pass);
    }
