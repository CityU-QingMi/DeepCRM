	@Test
	public void formContentIsNotDuplicated() throws Exception {

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new Spr15753Controller())
				.addFilter(new HttpPutFormContentFilter())
				.build();

		mockMvc.perform(put("/").content("d1=a&d2=s").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(content().string("d1:a, d2:s."));
	}
