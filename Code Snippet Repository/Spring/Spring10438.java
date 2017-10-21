	@Test
	public void testWithCommaSeparatedStringArray() throws Exception {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("stringArray", "bar");
		formData.add("stringArray", "abc");
		formData.add("stringArray", "123,def");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));
		assertEquals("Expected all three items to be bound", 3, this.testBean.getStringArray().length);

		formData.remove("stringArray");
		formData.add("stringArray", "123,def");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));
		assertEquals("Expected only 1 item to be bound", 1, this.testBean.getStringArray().length);
	}
