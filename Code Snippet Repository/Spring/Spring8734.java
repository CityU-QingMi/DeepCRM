	@Test
	public void deferredResultWithDelayedError() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/1").param("deferredResultWithDelayedError", "true"))
				.andExpect(request().asyncStarted())
				.andReturn();

		this.mockMvc.perform(asyncDispatch(mvcResult))
				.andExpect(status().is5xxServerError())
				.andExpect(content().string("Delayed Error"));
	}
