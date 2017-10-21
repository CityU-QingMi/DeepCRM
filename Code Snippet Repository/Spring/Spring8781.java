	@Test
	public void testDoesNotExist() throws Exception {

		String composer = "/ns:people/composers/composer[%s]";
		String performer = "/ns:people/performers/performer[%s]";

		this.mockMvc.perform(get("/music/people"))
			.andExpect(xpath(composer, musicNamespace, 0).doesNotExist())
			.andExpect(xpath(composer, musicNamespace, 5).doesNotExist())
			.andExpect(xpath(performer, musicNamespace, 0).doesNotExist())
			.andExpect(xpath(performer, musicNamespace, 3).doesNotExist())
			.andExpect(xpath(composer, musicNamespace, 0).node(nullValue()));
	}
