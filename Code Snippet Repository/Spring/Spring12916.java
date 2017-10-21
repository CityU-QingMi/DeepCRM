	@Test
	public void testFromMethodNameWithCustomBaseUrlViaInstance() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://example.org:9090/base");
		MvcUriComponentsBuilder mvcBuilder = MvcUriComponentsBuilder.relativeTo(builder);
		UriComponents uriComponents = mvcBuilder.withMethodName(ControllerWithMethods.class,
				"methodWithPathVariable", new Object[] {"1"}).build();

		assertEquals("http://example.org:9090/base/something/1/foo", uriComponents.toString());
		assertEquals("http://example.org:9090/base", builder.toUriString());
	}
