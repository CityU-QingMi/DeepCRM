	@Test
	public void mergeDoesNotCorruptPathInfoOnParent() throws Exception {
		String pathInfo = "/foo/bar";
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController())
				.defaultRequest(get("/"))
				.build();

		assertThat(mockMvc.perform(get(pathInfo)).andReturn().getRequest().getPathInfo(), equalTo(pathInfo));

		mockMvc.perform(requestBuilder);

		assertThat(mockMvc.perform(get(pathInfo)).andReturn().getRequest().getPathInfo(), equalTo(pathInfo));
	}
