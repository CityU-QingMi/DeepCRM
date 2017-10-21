    public void testBuildsValidatorsForAlias() {
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/SimpleAction-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/SimpleAction-validationAlias-validation.xml")),
                new ArrayList());
         actionValidatorManager.getValidators(SimpleAction.class, alias);
        mockValidatorFileParser.verify();
    }
