    public void testGetValidatorsFromInterface() {
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/SimpleAction-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/SimpleAction-validationAlias-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/DataAware-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/DataAware-validationAlias-validation.xml")),
                new ArrayList());
        actionValidatorManager.getValidators(SimpleAction3.class, alias);
        mockValidatorFileParser.verify();
    }
