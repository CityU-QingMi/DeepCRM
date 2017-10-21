    public void testGetValidatorsForInterface() {
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/DataAware-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/DataAware-validationAlias-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/DataAware2-validation.xml")),
                new ArrayList());
        actionValidatorManager.getValidators(DataAware2.class, alias);
        mockValidatorFileParser.verify();
    }
