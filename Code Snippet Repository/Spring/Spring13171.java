	@Test
	public void wrapConcurrentResult_CollectedValuesListWithResponseEntity() throws Exception {
		List<HttpMessageConverter<?>> converters = Collections.singletonList(new MappingJackson2HttpMessageConverter());
		ResolvableType elementType = ResolvableType.forClass(Bar.class);
		ReactiveTypeHandler.CollectedValuesList result = new ReactiveTypeHandler.CollectedValuesList(elementType);
		result.add(new Bar("foo"));
		result.add(new Bar("bar"));

		ContentNegotiationManager manager = new ContentNegotiationManager();
		this.returnValueHandlers.addHandler(new RequestResponseBodyMethodProcessor(converters, manager));
		ServletInvocableHandlerMethod hm = getHandlerMethod(new ResponseEntityHandler(), "handleFlux");
		hm = hm.wrapConcurrentResult(result);
		hm.invokeAndHandle(this.webRequest, this.mavContainer);

		assertEquals(200, this.response.getStatus());
		assertEquals("[{\"value\":\"foo\"},{\"value\":\"bar\"}]", this.response.getContentAsString());
	}
