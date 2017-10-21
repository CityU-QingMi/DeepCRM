	@Test
	public void testValidationWithOptionalField() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		MainBeanWithOptional mainBean = new MainBeanWithOptional();
		Errors errors = new BeanPropertyBindingResult(mainBean, "mainBean");
		validator.validate(mainBean, errors);
		Object rejected = errors.getFieldValue("inner.value");
		assertNull(rejected);
	}
