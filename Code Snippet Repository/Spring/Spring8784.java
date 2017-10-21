	@Test
	public void testFeedWithLinefeedChars() throws Exception {

//		Map<String, String> namespace = Collections.singletonMap("ns", "");

		standaloneSetup(new BlogFeedController()).build()
			.perform(get("/blog.atom").accept(MediaType.APPLICATION_ATOM_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_ATOM_XML))
				.andExpect(xpath("//feed/title").string("Test Feed"))
				.andExpect(xpath("//feed/icon").string("http://www.example.com/favicon.ico"));
	}
