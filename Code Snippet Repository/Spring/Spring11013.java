	@Test
	public void relativeUrls() throws Exception {
		assertThat(UriComponentsBuilder.fromUriString("http://example.com/foo/../bar").build().toString(), equalTo("http://example.com/foo/../bar"));
		assertThat(UriComponentsBuilder.fromUriString("http://example.com/foo/../bar").build().toUriString(), equalTo("http://example.com/foo/../bar"));
		assertThat(UriComponentsBuilder.fromUriString("http://example.com/foo/../bar").build().toUri().getPath(), equalTo("/foo/../bar"));
		assertThat(UriComponentsBuilder.fromUriString("../../").build().toString(), equalTo("../../"));
		assertThat(UriComponentsBuilder.fromUriString("../../").build().toUriString(), equalTo("../../"));
		assertThat(UriComponentsBuilder.fromUriString("../../").build().toUri().getPath(), equalTo("../../"));
		assertThat(UriComponentsBuilder.fromUriString("http://example.com").path("foo/../bar").build().toString(), equalTo("http://example.com/foo/../bar"));
		assertThat(UriComponentsBuilder.fromUriString("http://example.com").path("foo/../bar").build().toUriString(), equalTo("http://example.com/foo/../bar"));
		assertThat(UriComponentsBuilder.fromUriString("http://example.com").path("foo/../bar").build().toUri().getPath(), equalTo("/foo/../bar"));
	}
