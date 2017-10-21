	@Test
	public void testBindingWithNestedObjectCreation() throws Exception {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("spouse", "someValue");
		formData.add("spouse.name", "test");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));

		assertNotNull(this.testBean.getSpouse());
		assertEquals("test", testBean.getSpouse().getName());
	}
