	@Test
	public void testSpringValidationWithClassLevel() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		ValidPerson person = new ValidPerson();
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
	}
