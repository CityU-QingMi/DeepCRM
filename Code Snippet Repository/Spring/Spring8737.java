	@Test
	public void printAsyncResult() throws Exception {
		StringWriter writer = new StringWriter();

		MvcResult mvcResult = this.mockMvc.perform(get("/1").param("deferredResult", "true"))
				.andDo(print(writer))
				.andExpect(request().asyncStarted())
				.andReturn();

		assertTrue(writer.toString().contains("Async started = true"));
		writer = new StringWriter();

		this.asyncController.onMessage("Joe");

		this.mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print(writer))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string("{\"name\":\"Joe\",\"someDouble\":0.0,\"someBoolean\":false}"));

		assertTrue(writer.toString().contains("Async started = false"));
	}
