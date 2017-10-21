	@Test
	public void testSpringValidationFieldType() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		ValidPerson person = new ValidPerson();
		person.setName("Phil");
		person.getAddress().setStreet("Phil's Street");
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(person, "person");
		validator.validate(person, errors);
		assertEquals(1, errors.getErrorCount());
		assertThat("Field/Value type mismatch", errors.getFieldError("address").getRejectedValue(),
				instanceOf(ValidAddress.class));
	}
