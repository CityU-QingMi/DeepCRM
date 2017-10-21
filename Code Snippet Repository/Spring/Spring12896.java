	@Test
	public void resolveArgumentWithEmptyBody() throws Exception {
		this.servletRequest.setContent(new byte[0]);
		this.servletRequest.setContentType("application/json");

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(converters);

		HttpEntity<?> result = (HttpEntity<?>) processor.resolveArgument(this.paramSimpleBean,
				this.mavContainer, this.webRequest, this.binderFactory);

		assertNotNull(result);
		assertNull(result.getBody());
	}
