	@Test
	public void mergeRequestAttribute() throws Exception {
		String attrName = "PARENT";
		String attrValue = "VALUE";
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController())
				.defaultRequest(get("/").requestAttr(attrName, attrValue))
				.build();

		assertThat(mockMvc.perform(requestBuilder).andReturn().getRequest().getAttribute(attrName), equalTo(attrValue));
	}
