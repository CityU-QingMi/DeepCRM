	@Test
	public void getMediaTypeWithFavorPathExtensionOff() throws Exception {
		List<Resource> paths = Collections.singletonList(new ClassPathResource("test/", getClass()));
		ResourceWebHandler handler = new ResourceWebHandler();
		handler.setLocations(paths);
		handler.afterPropertiesSet();

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("")
				.header("Accept", "application/json,text/plain,*/*").build());
		setPathWithinHandlerMapping(exchange, "foo.html");
		handler.handle(exchange).block(TIMEOUT);

		assertEquals(MediaType.TEXT_HTML, exchange.getResponse().getHeaders().getContentType());
	}
