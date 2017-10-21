	@Test
	public void cookiesAreManaged() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new CookieController()).build();
		WebClient client = MockMvcWebClientBuilder.mockMvcSetup(this.mockMvc).build();

		assertThat(getResponse(client, "http://localhost/").getContentAsString(), equalTo("NA"));
		assertThat(postResponse(client, "http://localhost/?cookie=foo").getContentAsString(), equalTo("Set"));
		assertThat(getResponse(client, "http://localhost/").getContentAsString(), equalTo("foo"));
		assertThat(deleteResponse(client, "http://localhost/").getContentAsString(), equalTo("Delete"));
		assertThat(getResponse(client, "http://localhost/").getContentAsString(), equalTo("NA"));
	}
