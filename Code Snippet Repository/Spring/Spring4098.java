	@Test
	public void testValidationUtilsEmptyOrWhitespace() throws Exception {
		TestBean tb = new TestBean();
		Validator testValidator = new EmptyOrWhitespaceValidator();

		// Test null
		Errors errors = new BeanPropertyBindingResult(tb, "tb");
		testValidator.validate(tb, errors);
		assertTrue(errors.hasFieldErrors("name"));
		assertEquals("EMPTY_OR_WHITESPACE", errors.getFieldError("name").getCode());

		// Test empty String
		tb.setName("");
		errors = new BeanPropertyBindingResult(tb, "tb");
		testValidator.validate(tb, errors);
		assertTrue(errors.hasFieldErrors("name"));
		assertEquals("EMPTY_OR_WHITESPACE", errors.getFieldError("name").getCode());

		// Test whitespace String
		tb.setName(" ");
		errors = new BeanPropertyBindingResult(tb, "tb");
		testValidator.validate(tb, errors);
		assertTrue(errors.hasFieldErrors("name"));
		assertEquals("EMPTY_OR_WHITESPACE", errors.getFieldError("name").getCode());

		// Test OK
		tb.setName("Roddy");
		errors = new BeanPropertyBindingResult(tb, "tb");
		testValidator.validate(tb, errors);
		assertFalse(errors.hasFieldErrors("name"));
	}
