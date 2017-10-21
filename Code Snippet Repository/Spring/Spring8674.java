	@Test
	public void mergeHeader() throws Exception {
		String headerName = "PARENT";
		String headerValue = "VALUE";
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController())
					.defaultRequest(get("/").header(headerName, headerValue))
					.build();

		assertThat(mockMvc.perform(requestBuilder).andReturn().getRequest().getHeader(headerName), equalTo(headerValue));
	}
