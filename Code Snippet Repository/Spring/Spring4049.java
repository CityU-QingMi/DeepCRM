	@Test
	public void testValidatorWithNestedObjectNull() {
		TestBean tb = new TestBean();
		Errors errors = new BeanPropertyBindingResult(tb, "tb");
		Validator testValidator = new TestBeanValidator();
		testValidator.validate(tb, errors);
		errors.setNestedPath("spouse.");
		assertEquals("spouse.", errors.getNestedPath());
		Validator spouseValidator = new SpouseValidator();
		spouseValidator.validate(tb.getSpouse(), errors);
		errors.setNestedPath("");

		assertTrue(errors.hasFieldErrors("spouse"));
		assertEquals(1, errors.getFieldErrorCount("spouse"));
		assertEquals("SPOUSE_NOT_AVAILABLE", errors.getFieldError("spouse").getCode());
		assertEquals("tb", (errors.getFieldErrors("spouse").get(0)).getObjectName());
		assertEquals(null, (errors.getFieldErrors("spouse").get(0)).getRejectedValue());
	}
