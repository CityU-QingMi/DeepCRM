    public void testParserWithBadValidation() {
        InputStream is = ClassLoaderUtil.getResourceAsStream(testFileName3, this.getClass());

        boolean pass = false;
        try {
            parser.parseActionValidatorConfigs((ValidatorFactory) mockValidatorFactory.proxy(), is, testFileName3);
        } catch (XWorkException ex) {
            assertTrue("Wrong line number", 3 == ex.getLocation().getLineNumber());
            pass = true;
        }
        assertTrue("Validation file should have thrown exception", pass);
    }
