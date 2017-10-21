	@Test
	public void testContentNegotiationManager() throws Exception {
		loadBeanDefinitions("mvc-config-content-negotiation-manager.xml");

		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		ContentNegotiationManager manager = mapping.getContentNegotiationManager();

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo.xml");
		NativeWebRequest webRequest = new ServletWebRequest(request);
		assertEquals(Collections.singletonList(MediaType.valueOf("application/rss+xml")),
				manager.resolveMediaTypes(webRequest));

		ViewResolverComposite compositeResolver = this.appContext.getBean(ViewResolverComposite.class);
		assertNotNull(compositeResolver);
		assertEquals("Actual: " + compositeResolver.getViewResolvers(), 1, compositeResolver.getViewResolvers().size());

		ViewResolver resolver = compositeResolver.getViewResolvers().get(0);
		assertEquals(ContentNegotiatingViewResolver.class, resolver.getClass());
		ContentNegotiatingViewResolver cnvr = (ContentNegotiatingViewResolver) resolver;
		assertSame(manager, cnvr.getContentNegotiationManager());
	}
