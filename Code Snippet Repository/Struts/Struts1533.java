    public void testSameAliasWithDifferentClass() {
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/SimpleAction-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/SimpleAction-validationAlias-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/SimpleAction2-validation.xml")),
                new ArrayList());
        mockValidatorFileParser.expectAndReturn("parseActionValidatorConfigs",
                C.args(C.IS_NOT_NULL, C.IS_NOT_NULL, C.eq("com/opensymphony/xwork2/test/SimpleAction2-validationAlias-validation.xml")),
                new ArrayList());
        actionValidatorManager.getValidators(SimpleAction.class, alias);
        actionValidatorManager.getValidators(SimpleAction2.class, alias);
        mockValidatorFileParser.verify();
    }
