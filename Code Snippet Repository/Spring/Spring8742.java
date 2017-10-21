	@Test
	public void filterSkipped() throws Exception {
		standaloneSetup(new PersonController())
			.addFilter(new RedirectFilter(), "/p", "/person").build()
			.perform(post("/persons").param("name", "Andy"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/person/1"))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("id"))
				.andExpect(flash().attributeCount(1))
				.andExpect(flash().attribute("message", "success!"));
	}
