	@Test
	public void testNumber() throws Exception {

		String composerDouble = "/ns:people/composers/composer[%s]/someDouble";

		this.mockMvc.perform(get("/music/people"))
			.andExpect(xpath(composerDouble, musicNamespace, 1).number(21d))
			.andExpect(xpath(composerDouble, musicNamespace, 2).number(.0025))
			.andExpect(xpath(composerDouble, musicNamespace, 3).number(1.6035))
			.andExpect(xpath(composerDouble, musicNamespace, 4).number(Double.NaN))
			.andExpect(xpath(composerDouble, musicNamespace, 1).number(equalTo(21d)))  // Hamcrest..
			.andExpect(xpath(composerDouble, musicNamespace, 3).number(closeTo(1.6, .01)));
	}
