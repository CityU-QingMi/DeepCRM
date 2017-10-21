	@Test
	public void testNoStringArgumentValue() {
		TestBean testBean = new TestBean();
		testBean.setPassword("pass");
		testBean.setConfirmPassword("pass");

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(testBean, "testBean");
		validatorAdapter.validate(testBean, errors);

		assertThat(errors.getFieldErrorCount("password"), is(1));
		assertThat(errors.getFieldValue("password"), is("pass"));
		assertThat(messageSource.getMessage(errors.getFieldError("password"), Locale.ENGLISH),
				is("Size of Password is must be between 8 and 128"));
	}
