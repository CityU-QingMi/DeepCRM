	@Test
	public void exists() throws Exception {
		String composerByName = "$.composers[?(@.name == '%s')]";
		String performerByName = "$.performers[?(@.name == '%s')]";

		this.mockMvc.perform(get("/music/people"))
			.andExpect(jsonPath(composerByName, "Johann Sebastian Bach").exists())
			.andExpect(jsonPath(composerByName, "Johannes Brahms").exists())
			.andExpect(jsonPath(composerByName, "Edvard Grieg").exists())
			.andExpect(jsonPath(composerByName, "Robert Schumann").exists())
			.andExpect(jsonPath(performerByName, "Vladimir Ashkenazy").exists())
			.andExpect(jsonPath(performerByName, "Yehudi Menuhin").exists())
			.andExpect(jsonPath("$.composers[0]").exists())
			.andExpect(jsonPath("$.composers[1]").exists())
			.andExpect(jsonPath("$.composers[2]").exists())
			.andExpect(jsonPath("$.composers[3]").exists());
	}
