	@Test
	public void testApplyMessageSourceResolvableToStringArgumentValueWithResolvedLogicalFieldName() {
		TestBean testBean = new TestBean();
		testBean.setPassword("password");
		testBean.setConfirmPassword("PASSWORD");

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(testBean, "testBean");
		validatorAdapter.validate(testBean, errors);

		assertThat(errors.getFieldErrorCount("password"), is(1));
		assertThat(errors.getFieldValue("password"), is("password"));
		assertThat(messageSource.getMessage(errors.getFieldError("password"), Locale.ENGLISH),
				is("Password must be same value with Password(Confirm)"));
	}
