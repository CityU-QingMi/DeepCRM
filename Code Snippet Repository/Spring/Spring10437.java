	@Test
	public void testFieldDefaultNonBoolean() throws Exception {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("!name", "anonymous");
		formData.add("name", "Scott");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));
		assertEquals("Scott", this.testBean.getName());

		formData.remove("name");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));
		assertEquals("anonymous", this.testBean.getName());
	}
