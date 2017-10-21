	@Test
	public void testSpringValidationWithErrorInSetElement() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		ValidPerson person = new ValidPerson();
		person.getAddressSet().add(new ValidAddress());
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(person, "person");
		validator.validate(person, result);
		assertEquals(3, result.getErrorCount());
		FieldError fieldError = result.getFieldError("name");
		assertEquals("name", fieldError.getField());
		fieldError = result.getFieldError("address.street");
		assertEquals("address.street", fieldError.getField());
		fieldError = result.getFieldError("addressSet[].street");
		assertEquals("addressSet[].street", fieldError.getField());
	}
