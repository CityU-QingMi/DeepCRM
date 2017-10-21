	@Test
	public void invalidHttpMethod() throws Exception {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.method("BAZ", null)
				.url("http://localhost:" + port + "/")
				.build();

		try (Response response = client.newCall(request).execute()) {
			assertEquals("BAR", response.body().string());
		}
	}
