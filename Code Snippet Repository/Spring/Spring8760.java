	@Test
	public void save() throws Exception {
		this.mockMvc.perform(post("/persons").param("name", "Andy"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/persons/Joe"))
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("name"))
			.andExpect(flash().attributeCount(1))
			.andExpect(flash().attribute("message", "success!"));
	}
