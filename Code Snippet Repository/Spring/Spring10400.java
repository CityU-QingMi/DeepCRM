	@Test
	public void resolveMediaTypesFromMediaTypeFactory() throws Exception {

		this.servletRequest.setRequestURI("test.xls");

		PathExtensionContentNegotiationStrategy strategy = new PathExtensionContentNegotiationStrategy();
		List<MediaType> mediaTypes = strategy.resolveMediaTypes(this.webRequest);

		assertEquals(Arrays.asList(new MediaType("application", "vnd.ms-excel")), mediaTypes);
	}
