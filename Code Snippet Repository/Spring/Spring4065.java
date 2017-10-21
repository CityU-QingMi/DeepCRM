	@Test
	public void testAddAllErrors() {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("age", "32x");
		binder.bind(pvs);
		Errors errors = binder.getBindingResult();

		BeanPropertyBindingResult errors2 = new BeanPropertyBindingResult(rod, "person");
		errors.rejectValue("name", "badName");
		errors.addAllErrors(errors2);

		FieldError ageError = errors.getFieldError("age");
		assertEquals("typeMismatch", ageError.getCode());
		FieldError nameError = errors.getFieldError("name");
		assertEquals("badName", nameError.getCode());
	}
