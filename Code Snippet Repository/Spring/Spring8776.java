	@Test
	public void equality() throws Exception {
		this.mockMvc.perform(get("/music/people"))
			.andExpect(jsonPath("$.composers[0].name").value("Johann Sebastian Bach"))
			.andExpect(jsonPath("$.performers[1].name").value("Yehudi Menuhin"));

		// Hamcrest matchers...
		this.mockMvc.perform(get("/music/people"))
			.andExpect(jsonPath("$.composers[0].name").value(equalTo("Johann Sebastian Bach")))
			.andExpect(jsonPath("$.performers[1].name").value(equalTo("Yehudi Menuhin")));
	}
