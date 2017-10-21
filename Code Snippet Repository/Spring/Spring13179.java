	@Test
	public void wrapConcurrentResult_CollectedValuesList() throws Exception {
		List<HttpMessageConverter<?>> converters = Collections.singletonList(new MappingJackson2HttpMessageConverter());
		ResolvableType elementType = ResolvableType.forClass(List.class);
		ReactiveTypeHandler.CollectedValuesList result = new ReactiveTypeHandler.CollectedValuesList(elementType);
		result.add(Arrays.asList("foo1", "bar1"));
		result.add(Arrays.asList("foo2", "bar2"));

		ContentNegotiationManager manager = new ContentNegotiationManager();
		this.returnValueHandlers.addHandler(new RequestResponseBodyMethodProcessor(converters, manager));
		ServletInvocableHandlerMethod hm = getHandlerMethod(new MethodLevelResponseBodyHandler(), "handleFluxOfLists");
		hm = hm.wrapConcurrentResult(result);
		hm.invokeAndHandle(this.webRequest, this.mavContainer);

		assertEquals(200, this.response.getStatus());
		assertEquals("[[\"foo1\",\"bar1\"],[\"foo2\",\"bar2\"]]", this.response.getContentAsString());
	}
