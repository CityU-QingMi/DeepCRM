	@Test
	public void infoUrl() throws Exception {
		testInfoUrl("http", "http");
		testInfoUrl("http", "http");
		testInfoUrl("https", "https");
		testInfoUrl("https", "https");
		testInfoUrl("ws", "http");
		testInfoUrl("ws", "http");
		testInfoUrl("wss", "https");
		testInfoUrl("wss", "https");
	}
