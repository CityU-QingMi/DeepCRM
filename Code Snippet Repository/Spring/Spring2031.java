	@Test
	public void testListElementConstraint() {
		BeanWithListElementConstraint bean = new BeanWithListElementConstraint();
		bean.setProperty(Arrays.asList("no", "element", "can", "be", null));

		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(bean, "bean");
		validatorAdapter.validate(bean, errors);

		assertThat(errors.getFieldErrorCount("property[4]"), is(1));
		assertNull(errors.getFieldValue("property[4]"));
	}
