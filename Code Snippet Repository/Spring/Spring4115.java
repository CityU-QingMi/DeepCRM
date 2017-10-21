	@Test
	public void testListValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		ListContainer listContainer = new ListContainer();
		listContainer.addString("A");
		listContainer.addString("X");

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(listContainer, "listContainer");
		errors.initConversion(new DefaultConversionService());
		validator.validate(listContainer, errors);

		FieldError fieldError = errors.getFieldError("list[1]");
		assertEquals("X", errors.getFieldValue("list[1]"));
	}
