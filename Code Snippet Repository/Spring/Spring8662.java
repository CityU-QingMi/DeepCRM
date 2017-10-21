	@Test
	public void requestParametersAreClearedBetweenInvocations() throws Exception {
		this.mvc.perform(get("/"))
			.andExpect(content().string(HELLO));

		this.mvc.perform(get("/").param(ENIGMA, ""))
			.andExpect(content().string(ENIGMA));

		this.mvc.perform(get("/"))
			.andExpect(content().string(HELLO));
	}
