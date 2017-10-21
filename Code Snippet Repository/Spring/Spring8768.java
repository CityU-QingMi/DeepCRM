	@Test
	public void testContentAsString() throws Exception {

		this.mockMvc.perform(get("/handle").accept(MediaType.TEXT_PLAIN))
			.andExpect(content().string("Hello world!"));

		this.mockMvc.perform(get("/handleUtf8"))
			.andExpect(content().string("\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01"));

		// Hamcrest matchers...
		this.mockMvc.perform(get("/handle").accept(MediaType.TEXT_PLAIN)).andExpect(content().string(equalTo("Hello world!")));
		this.mockMvc.perform(get("/handleUtf8")).andExpect(content().string(equalTo("\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01")));
	}
