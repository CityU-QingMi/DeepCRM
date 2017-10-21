	@Test
	public void testSimpleValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		ValidPerson person = new ValidPerson();
		Set<ConstraintViolation<ValidPerson>> result = validator.validate(person);
		assertEquals(2, result.size());
		for (ConstraintViolation<ValidPerson> cv : result) {
			String path = cv.getPropertyPath().toString();
			if ("name".equals(path) || "address.street".equals(path)) {
				assertTrue(cv.getConstraintDescriptor().getAnnotation() instanceof NotNull);
			}
			else {
				fail("Invalid constraint violation with path '" + path + "'");
			}
		}

		Validator nativeValidator = validator.unwrap(Validator.class);
		assertTrue(nativeValidator.getClass().getName().startsWith("org.hibernate"));
		assertTrue(validator.unwrap(ValidatorFactory.class) instanceof HibernateValidatorFactory);
		assertTrue(validator.unwrap(HibernateValidatorFactory.class) instanceof HibernateValidatorFactory);

		validator.destroy();
	}
