	@Test
	public void testMapEntryConstraint() {
		Map<String, String> property = new HashMap<>();
		property.put(null, null);

		BeanWithMapEntryConstraint bean = new BeanWithMapEntryConstraint();
		bean.setProperty(property);

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(bean, "bean");
		validatorAdapter.validate(bean, errors);

		assertTrue(errors.hasFieldErrors("property[]"));
		assertNull(errors.getFieldValue("property[]"));
	}
