	@Test
	public void testBindingWithNestedObjectCreationAndWrongOrder() throws Exception {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("spouse.name", "test");
		formData.add("spouse", "someValue");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));

		assertNotNull(this.testBean.getSpouse());
		assertEquals("test", this.testBean.getSpouse().getName());
	}
