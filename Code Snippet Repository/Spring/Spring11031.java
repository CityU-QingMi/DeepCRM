	@Test
	public void equalsOpaqueUriComponents() throws Exception {
		UriComponents uriComponents1 = UriComponentsBuilder.fromUriString("http:example.com/foo/bar").build();
		UriComponents uriComponents2 = UriComponentsBuilder.fromUriString("http:example.com/foo/bar").build();
		UriComponents uriComponents3 = UriComponentsBuilder.fromUriString("http:example.com/foo/bin").build();
		assertThat(uriComponents1, instanceOf(OpaqueUriComponents.class));
		assertThat(uriComponents1, equalTo(uriComponents1));
		assertThat(uriComponents1, equalTo(uriComponents2));
		assertThat(uriComponents1, not(equalTo(uriComponents3)));
	}
