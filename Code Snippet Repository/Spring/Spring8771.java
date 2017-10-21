	@Test
	public void testEqualTo() throws Exception {
		this.mockMvc.perform(post("/persons"))
			.andExpect(flash().attribute("one", "1"))
			.andExpect(flash().attribute("two", 2.222))
			.andExpect(flash().attribute("three", new URL("http://example.com")))
			.andExpect(flash().attribute("one", equalTo("1")))	// Hamcrest...
			.andExpect(flash().attribute("two", equalTo(2.222)))
			.andExpect(flash().attribute("three", equalTo(new URL("http://example.com"))));
	}
