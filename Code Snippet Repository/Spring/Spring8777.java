	@Test
	public void hamcrestMatcherWithParameterizedJsonPath() throws Exception {
		String composerName = "$.composers[%s].name";
		String performerName = "$.performers[%s].name";

		this.mockMvc.perform(get("/music/people"))
			.andExpect(jsonPath(composerName, 0).value(startsWith("Johann")))
			.andExpect(jsonPath(performerName, 0).value(endsWith("Ashkenazy")))
			.andExpect(jsonPath(performerName, 1).value(containsString("di Me")))
			.andExpect(jsonPath(composerName, 1).value(isIn(Arrays.asList("Johann Sebastian Bach", "Johannes Brahms"))));
	}
