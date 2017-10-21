	@Test
	public void saveSpecial() throws Exception {
		this.mockMvc.perform(post("/people").param("name", "Andy"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/persons/Joe"))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("name"))
				.andExpect(flash().attributeCount(1))
				.andExpect(flash().attribute("message", "success!"));
	}
