	@Test
	public void testString() throws Exception {

		String composerName = "/ns:people/composers/composer[%s]/name";
		String performerName = "/ns:people/performers/performer[%s]/name";

		this.mockMvc.perform(get("/music/people"))
			.andExpect(xpath(composerName, musicNamespace, 1).string("Johann Sebastian Bach"))
			.andExpect(xpath(composerName, musicNamespace, 2).string("Johannes Brahms"))
			.andExpect(xpath(composerName, musicNamespace, 3).string("Edvard Grieg"))
			.andExpect(xpath(composerName, musicNamespace, 4).string("Robert Schumann"))
			.andExpect(xpath(performerName, musicNamespace, 1).string("Vladimir Ashkenazy"))
			.andExpect(xpath(performerName, musicNamespace, 2).string("Yehudi Menuhin"))
			.andExpect(xpath(composerName, musicNamespace, 1).string(equalTo("Johann Sebastian Bach"))) // Hamcrest..
			.andExpect(xpath(composerName, musicNamespace, 1).string(startsWith("Johann")))
			.andExpect(xpath(composerName, musicNamespace, 1).string(notNullValue()));
	}
