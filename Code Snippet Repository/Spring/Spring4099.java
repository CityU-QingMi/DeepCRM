	@Test
	public void testValidationUtilsEmptyOrWhitespaceVariants() {
		TestBean tb = new TestBean();
		tb.setName(" ");

		Errors errors = new BeanPropertyBindingResult(tb, "tb");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "EMPTY_OR_WHITESPACE", new Object[] {"arg"});
		assertTrue(errors.hasFieldErrors("name"));
		assertEquals("EMPTY_OR_WHITESPACE", errors.getFieldError("name").getCode());
		assertEquals("arg", errors.getFieldError("name").getArguments()[0]);

		errors = new BeanPropertyBindingResult(tb, "tb");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "EMPTY_OR_WHITESPACE", new Object[] {"arg"}, "msg");
		assertTrue(errors.hasFieldErrors("name"));
		assertEquals("EMPTY_OR_WHITESPACE", errors.getFieldError("name").getCode());
		assertEquals("arg", errors.getFieldError("name").getArguments()[0]);
		assertEquals("msg", errors.getFieldError("name").getDefaultMessage());
	}
