    public void testBuildsValidatorsForAliasError() {
        boolean pass = false;
        try {
            mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/TestBean-validation.xml")),
                new ArrayList());
            mockValidatorFileParser.expectAndThrow("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/TestBean-badtest-validation.xml")),
                new ConfigurationException());
            List validatorList = actionValidatorManager.getValidators(TestBean.class, "badtest");
        } catch (XWorkException ex) {
            pass = true;
        }
        mockValidatorFileParser.verify();
        assertTrue("Didn't throw exception on load failure", pass);
    }
