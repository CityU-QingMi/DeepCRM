	@Test
	public void testFromMappingName() throws Exception {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setServletContext(new MockServletContext());
		context.register(WebConfig.class);
		context.refresh();

		this.request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
		this.request.setServerName("example.org");
		this.request.setServerPort(9999);
		this.request.setContextPath("/base");

		String mappingName = "PAC#getAddressesForCountry";
		String url = MvcUriComponentsBuilder.fromMappingName(mappingName).arg(0, "DE").buildAndExpand(123);
		assertEquals("/base/people/123/addresses/DE", url);
	}
