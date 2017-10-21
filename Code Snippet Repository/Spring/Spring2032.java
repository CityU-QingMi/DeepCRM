	@Test
	public void testMapValueConstraint() {
		Map<String, String> property = new HashMap<>();
		property.put("no value can be", null);

		BeanWithMapEntryConstraint bean = new BeanWithMapEntryConstraint();
		bean.setProperty(property);

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(bean, "bean");
		validatorAdapter.validate(bean, errors);

		assertThat(errors.getFieldErrorCount("property[no value can be]"), is(1));
		assertNull(errors.getFieldValue("property[no value can be]"));
	}
