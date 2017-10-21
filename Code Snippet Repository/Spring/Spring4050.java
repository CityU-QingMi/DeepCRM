	@Test
	public void testNestedValidatorWithoutNestedPath() {
		TestBean tb = new TestBean();
		tb.setName("XXX");
		Errors errors = new BeanPropertyBindingResult(tb, "tb");
		Validator spouseValidator = new SpouseValidator();
		spouseValidator.validate(tb, errors);

		assertTrue(errors.hasGlobalErrors());
		assertEquals(1, errors.getGlobalErrorCount());
		assertEquals("SPOUSE_NOT_AVAILABLE", errors.getGlobalError().getCode());
		assertEquals("tb", (errors.getGlobalErrors().get(0)).getObjectName());
	}
