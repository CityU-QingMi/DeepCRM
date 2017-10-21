	@Test
	public void hamcrestMatchersWithParameterizedJsonPaths() throws Exception {
		String composerName = "$.composers[%s].name";
		String performerName = "$.performers[%s].name";

		this.mockServer.expect(requestTo("/composers"))
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath(composerName, 0).value(startsWith("Johann")))
			.andExpect(jsonPath(performerName, 0).value(endsWith("Ashkenazy")))
			.andExpect(jsonPath(performerName, 1).value(containsString("di Me")))
			.andExpect(jsonPath(composerName, 1).value(isIn(Arrays.asList("Johann Sebastian Bach", "Johannes Brahms"))))
			.andRespond(withSuccess());
	}
