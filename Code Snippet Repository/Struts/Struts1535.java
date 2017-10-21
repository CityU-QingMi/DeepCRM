    public void testParserActionLevelValidatorsShouldBeBeforeFieldLevelValidators() throws Exception {
        InputStream is = ClassLoaderUtil.getResourceAsStream(testFileName2, this.getClass());

        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("expression")), ExpressionValidator.class.getName());
        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("required")), RequiredFieldValidator.class.getName());
        List configs = parser.parseActionValidatorConfigs((ValidatorFactory) mockValidatorFactory.proxy(), is, testFileName2);
        mockValidatorFactory.verify();

        ValidatorConfig valCfg0 = (ValidatorConfig) configs.get(0);
        ValidatorConfig valCfg1 = (ValidatorConfig) configs.get(1);

        assertNotNull(configs);
        assertEquals(configs.size(), 2);

        assertEquals("expression", valCfg0.getType());
        assertFalse(valCfg0.isShortCircuit());
        assertEquals(valCfg0.getDefaultMessage(), "an expression error message");
        assertEquals(valCfg0.getParams().get("expression"), "false");

        assertEquals("required", valCfg1.getType());
        assertFalse(valCfg1.isShortCircuit());
        assertEquals(valCfg1.getDefaultMessage(), "a field error message");
    }
