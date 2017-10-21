	@Test
	public void equalsHierarchicalUriComponents() throws Exception {
		UriComponents uriComponents1 = UriComponentsBuilder.fromUriString("http://example.com").path("/{foo}").query("bar={baz}").build();
		UriComponents uriComponents2 = UriComponentsBuilder.fromUriString("http://example.com").path("/{foo}").query("bar={baz}").build();
		UriComponents uriComponents3 = UriComponentsBuilder.fromUriString("http://example.com").path("/{foo}").query("bin={baz}").build();
		assertThat(uriComponents1, instanceOf(HierarchicalUriComponents.class));
		assertThat(uriComponents1, equalTo(uriComponents1));
		assertThat(uriComponents1, equalTo(uriComponents2));
		assertThat(uriComponents1, not(equalTo(uriComponents3)));
	}
