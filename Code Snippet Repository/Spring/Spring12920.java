	@Test
	public void testFromMappingNameWithCustomBaseUrl() throws Exception {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setServletContext(new MockServletContext());
		context.register(WebConfig.class);
		context.refresh();

		this.request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);

		UriComponentsBuilder baseUrl = UriComponentsBuilder.fromUriString("http://example.org:9999/base");
		MvcUriComponentsBuilder mvcBuilder = MvcUriComponentsBuilder.relativeTo(baseUrl);
		String url = mvcBuilder.withMappingName("PAC#getAddressesForCountry").arg(0, "DE").buildAndExpand(123);
		assertEquals("http://example.org:9999/base/people/123/addresses/DE", url);
	}
