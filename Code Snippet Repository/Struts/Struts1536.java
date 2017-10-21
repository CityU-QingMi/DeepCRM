    public void testParser() {
        InputStream is = ClassLoaderUtil.getResourceAsStream(testFileName, this.getClass());

        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("expression")), ExpressionValidator.class.getName());
        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("expression")), ExpressionValidator.class.getName());
        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("required")), RequiredFieldValidator.class.getName());
        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("required")), RequiredFieldValidator.class.getName());
        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("int")), IntRangeFieldValidator.class.getName());
        mockValidatorFactory.expectAndReturn("lookupRegisteredValidatorType", C.args(C.eq("regex")), RegexFieldValidator.class.getName());
        List configs = parser.parseActionValidatorConfigs((ValidatorFactory) mockValidatorFactory.proxy(), is, testFileName);
        mockValidatorFactory.verify();


        assertNotNull(configs);
        assertEquals(6, configs.size());


        ValidatorConfig cfg = (ValidatorConfig) configs.get(0);
        assertEquals("expression", cfg.getType());
        assertFalse(cfg.isShortCircuit());

        cfg = (ValidatorConfig) configs.get(1);
        assertEquals("expression", cfg.getType());
        assertTrue(cfg.isShortCircuit());

        cfg = (ValidatorConfig) configs.get(2);
        assertEquals("required", cfg.getType());
        assertEquals("foo", cfg.getParams().get("fieldName"));
        assertEquals("You must enter a value for foo.", cfg.getDefaultMessage());
        assertEquals(4, cfg.getLocation().getLineNumber());

        cfg = (ValidatorConfig) configs.get(3);
        assertEquals("required", cfg.getType());
        assertTrue(cfg.isShortCircuit());

        cfg = (ValidatorConfig) configs.get(4);
        assertEquals("int", cfg.getType());
        assertFalse(cfg.isShortCircuit());

        cfg = (ValidatorConfig) configs.get(5);
        assertEquals("regex", cfg.getType());
        assertFalse(cfg.isShortCircuit());
        assertEquals("([aAbBcCdD][123][eEfFgG][456])", cfg.getParams().get("expression"));
    }
