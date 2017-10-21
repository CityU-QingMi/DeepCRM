	@Test
	public void verifyExampleInClassLevelJavadoc() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		WebClient webClient = new WebClient();

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(TestController.class).build();
		MockMvcWebConnection mockConnection = new MockMvcWebConnection(mockMvc, webClient);

		WebRequestMatcher cdnMatcher = new UrlRegexRequestMatcher(".*?//code.jquery.com/.*");
		WebConnection httpConnection = new HttpWebConnection(webClient);
		WebConnection webConnection = new DelegatingWebConnection(mockConnection, new DelegateWebConnection(cdnMatcher, httpConnection));

		webClient.setWebConnection(webConnection);

		Page page = webClient.getPage("http://code.jquery.com/jquery-1.11.0.min.js");
		assertThat(page.getWebResponse().getStatusCode(), equalTo(200));
		assertThat(page.getWebResponse().getContentAsString(), not(isEmptyString()));
	}
