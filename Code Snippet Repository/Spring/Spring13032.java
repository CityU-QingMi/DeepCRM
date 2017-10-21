	@Test
	public void resolveArgumentRawTypeFromParameterizedType() throws Exception {
		String content = "fruit=apple&vegetable=kale";
		this.servletRequest.setMethod("GET");
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new AllEncompassingFormHttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		@SuppressWarnings("unchecked")
		MultiValueMap<String, String> result = (MultiValueMap<String, String>) processor.resolveArgument(
				paramMultiValueMap, container, request, factory);

		assertNotNull(result);
		assertEquals("apple", result.getFirst("fruit"));
		assertEquals("kale", result.getFirst("vegetable"));
	}
