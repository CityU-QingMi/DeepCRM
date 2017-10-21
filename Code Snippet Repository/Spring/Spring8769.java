	@Test
	public void testCharacterEncoding() throws Exception {

		this.mockMvc.perform(get("/handle").accept(MediaType.TEXT_PLAIN))
			.andExpect(content().encoding("ISO-8859-1"))
			.andExpect(content().string(containsString("world")));

		this.mockMvc.perform(get("/handleUtf8"))
			.andExpect(content().encoding("UTF-8"))
			.andExpect(content().bytes("\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01".getBytes("UTF-8")));
	}
