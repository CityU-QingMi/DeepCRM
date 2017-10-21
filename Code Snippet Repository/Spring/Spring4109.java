	@Test
	public void testApplyMessageSourceResolvableToStringArgumentValueWithUnresolvedLogicalFieldName() {
		TestBean testBean = new TestBean();
		testBean.setEmail("test@example.com");
		testBean.setConfirmEmail("TEST@EXAMPLE.IO");

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(testBean, "testBean");
		validatorAdapter.validate(testBean, errors);

		assertThat(errors.getFieldErrorCount("email"), is(1));
		assertThat(errors.getFieldValue("email"), is("test@example.com"));
		assertThat(errors.getFieldErrorCount("confirmEmail"), is(1));
		assertThat(messageSource.getMessage(errors.getFieldError("email"), Locale.ENGLISH),
				is("email must be same value with confirmEmail"));
		assertThat(messageSource.getMessage(errors.getFieldError("confirmEmail"), Locale.ENGLISH),
				is("Email required"));
	}
