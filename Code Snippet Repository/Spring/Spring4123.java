	@Test
	public void testSpringValidationWithAutowiredValidator() throws Exception {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
				LocalValidatorFactoryBean.class);
		LocalValidatorFactoryBean validator = ctx.getBean(LocalValidatorFactoryBean.class);

		ValidPerson person = new ValidPerson();
		person.expectsAutowiredValidator = true;
		person.setName("Juergen");
		person.getAddress().setStreet("Juergen's Street");
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(person, "person");
		validator.validate(person, result);
		assertEquals(1, result.getErrorCount());
		ObjectError globalError = result.getGlobalError();
		List<String> errorCodes = Arrays.asList(globalError.getCodes());
		assertEquals(2, errorCodes.size());
		assertTrue(errorCodes.contains("NameAddressValid.person"));
		assertTrue(errorCodes.contains("NameAddressValid"));
		ctx.close();
	}
