	@Test
	public void testFieldDefault() throws Exception {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("!postProcessed", "off");
		formData.add("postProcessed", "on");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));
		assertTrue(this.testBean.isPostProcessed());

		formData.remove("postProcessed");
		this.binder.bind(exchange(formData)).block(Duration.ofMillis(5000));
		assertFalse(this.testBean.isPostProcessed());
	}
