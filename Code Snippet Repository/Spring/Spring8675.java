	@Test
	public void mergeSession() throws Exception {
		String attrName = "PARENT";
		String attrValue = "VALUE";
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController())
				.defaultRequest(get("/").sessionAttr(attrName, attrValue))
				.build();

		assertThat(mockMvc.perform(requestBuilder).andReturn().getRequest().getSession().getAttribute(attrName), equalTo(attrValue));
	}
