	@Test
	public void testInnerBeanValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		MainBean mainBean = new MainBean();
		Errors errors = new BeanPropertyBindingResult(mainBean, "mainBean");
		validator.validate(mainBean, errors);
		Object rejected = errors.getFieldValue("inner.value");
		assertNull(rejected);
	}
