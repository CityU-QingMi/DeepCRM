    public void testValidatorWithI18nMessage() throws Exception {
        try (InputStream is = ClassLoaderUtil.getResourceAsStream(testFileName6, this.getClass())) {
            mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("requiredstring")), RequiredStringValidator.class.getName());
            mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("requiredstring")), RequiredStringValidator.class.getName());

            List validatorConfigs = parser.parseActionValidatorConfigs((ValidatorFactory) mockValidatorFactory.proxy(), is, "-//OpenSymphony Group//XWork Validator 1.0.3//EN");
            mockValidatorFactory.verify();

            assertEquals(validatorConfigs.size(), 2);

            assertEquals(((ValidatorConfig)validatorConfigs.get(0)).getParams().get("fieldName"), "name");
            assertEquals(((ValidatorConfig)validatorConfigs.get(0)).getMessageParams().length, 0);
            assertEquals(((ValidatorConfig)validatorConfigs.get(0)).getMessageKey(), "error.name");
            assertEquals(((ValidatorConfig)validatorConfigs.get(0)).getDefaultMessage(), "default message 1");
            assertEquals(((ValidatorConfig)validatorConfigs.get(0)).getParams().size(), 1);
            assertEquals(((ValidatorConfig)validatorConfigs.get(0)).getType(), "requiredstring");

            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getParams().get("fieldName"), "address");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageParams().length, 5);
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageParams()[0], "'tmjee'");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageParams()[1], "'phil'");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageParams()[2], "'rainer'");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageParams()[3], "'hopkins'");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageParams()[4], "'jimmy'");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getMessageKey(), "error.address");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getDefaultMessage(), "The Default Message");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getParams().size(), 3);
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getParams().get("trim"), "true");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getParams().get("anotherParam"), "anotherValue");
            assertEquals(((ValidatorConfig)validatorConfigs.get(1)).getType(), "requiredstring");
        }
    }
