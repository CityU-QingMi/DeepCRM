	@Test
	public void testSpringValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		ValidPerson person = new ValidPerson();
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(person, "person");
		validator.validate(person, result);
		assertEquals(2, result.getErrorCount());
		FieldError fieldError = result.getFieldError("name");
		assertEquals("name", fieldError.getField());
		List<String> errorCodes = Arrays.asList(fieldError.getCodes());
		assertEquals(4, errorCodes.size());
		assertTrue(errorCodes.contains("NotNull.person.name"));
		assertTrue(errorCodes.contains("NotNull.name"));
		assertTrue(errorCodes.contains("NotNull.java.lang.String"));
		assertTrue(errorCodes.contains("NotNull"));
		fieldError = result.getFieldError("address.street");
		assertEquals("address.street", fieldError.getField());
		errorCodes = Arrays.asList(fieldError.getCodes());
		assertEquals(5, errorCodes.size());
		assertTrue(errorCodes.contains("NotNull.person.address.street"));
		assertTrue(errorCodes.contains("NotNull.address.street"));
		assertTrue(errorCodes.contains("NotNull.street"));
		assertTrue(errorCodes.contains("NotNull.java.lang.String"));
		assertTrue(errorCodes.contains("NotNull"));
	}
