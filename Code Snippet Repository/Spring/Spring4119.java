	@Test
	public void testSimpleValidationWithClassLevel() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		ValidPerson person = new ValidPerson();
		person.setName("Juergen");
		person.getAddress().setStreet("Juergen's Street");
		Set<ConstraintViolation<ValidPerson>> result = validator.validate(person);
		assertEquals(1, result.size());
		Iterator<ConstraintViolation<ValidPerson>> iterator = result.iterator();
		ConstraintViolation<?> cv = iterator.next();
		assertEquals("", cv.getPropertyPath().toString());
		assertTrue(cv.getConstraintDescriptor().getAnnotation() instanceof NameAddressValid);
	}
