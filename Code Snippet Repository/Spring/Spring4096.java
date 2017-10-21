	@Test
	public void testValidationUtilsSunnyDay() throws Exception {
		TestBean tb = new TestBean("");

		Validator testValidator = new EmptyValidator();
		tb.setName(" ");
		Errors errors = new BeanPropertyBindingResult(tb, "tb");
		testValidator.validate(tb, errors);
		assertFalse(errors.hasFieldErrors("name"));

		tb.setName("Roddy");
		errors = new BeanPropertyBindingResult(tb, "tb");
		testValidator.validate(tb, errors);
		assertFalse(errors.hasFieldErrors("name"));
	}
