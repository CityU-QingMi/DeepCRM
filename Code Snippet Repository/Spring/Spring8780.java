	@Test
	public void testExists() throws Exception {

		String composer = "/ns:people/composers/composer[%s]";
		String performer = "/ns:people/performers/performer[%s]";

		this.mockMvc.perform(get("/music/people"))
			.andExpect(xpath(composer, musicNamespace, 1).exists())
			.andExpect(xpath(composer, musicNamespace, 2).exists())
			.andExpect(xpath(composer, musicNamespace, 3).exists())
			.andExpect(xpath(composer, musicNamespace, 4).exists())
			.andExpect(xpath(performer, musicNamespace, 1).exists())
			.andExpect(xpath(performer, musicNamespace, 2).exists())
			.andExpect(xpath(composer, musicNamespace, 1).node(notNullValue()));
	}
