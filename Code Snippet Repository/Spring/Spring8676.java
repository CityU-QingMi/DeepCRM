	@Test
	public void mergeParameter() throws Exception {
		String paramName = "PARENT";
		String paramValue = "VALUE";
		String paramValue2 = "VALUE2";
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController())
				.defaultRequest(get("/").param(paramName, paramValue, paramValue2))
				.build();

		MockHttpServletRequest performedRequest = mockMvc.perform(requestBuilder).andReturn().getRequest();
		assertThat(asList(performedRequest.getParameterValues(paramName)), contains(paramValue, paramValue2));
	}
