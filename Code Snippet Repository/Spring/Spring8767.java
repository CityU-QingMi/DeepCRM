	@Test
	public void testContentType() throws Exception {
		this.mockMvc.perform(get("/handle").accept(MediaType.TEXT_PLAIN))
			.andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1")))
			.andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
			.andExpect(content().contentTypeCompatibleWith("text/plain"))
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));

		this.mockMvc.perform(get("/handleUtf8"))
			.andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
			.andExpect(content().contentType("text/plain;charset=UTF-8"))
			.andExpect(content().contentTypeCompatibleWith("text/plain"))
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
	}
