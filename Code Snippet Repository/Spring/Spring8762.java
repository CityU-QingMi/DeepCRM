	@Test
	public void getPerson() throws Exception {
		this.mockMvc.perform(get("/persons/Joe").flashAttr("message", "success!"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("persons/index"))
			.andExpect(model().size(2))
			.andExpect(model().attribute("person", new Person("Joe")))
			.andExpect(model().attribute("message", "success!"))
			.andExpect(flash().attributeCount(0));
	}
