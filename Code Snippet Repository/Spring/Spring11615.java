	@Test
	public void getVersionedResource() throws Exception {
		VersionResourceResolver versionResolver = new VersionResourceResolver();
		versionResolver.addFixedVersionStrategy("versionString", "/**");
		this.handler.setResourceResolvers(Arrays.asList(versionResolver, new PathResourceResolver()));
		this.handler.afterPropertiesSet();

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		setPathWithinHandlerMapping(exchange, "versionString/foo.css");
		this.handler.handle(exchange).block(TIMEOUT);

		assertEquals("\"versionString\"", exchange.getResponse().getHeaders().getETag());
		assertEquals("bytes", exchange.getResponse().getHeaders().getFirst("Accept-Ranges"));
		assertEquals(1, exchange.getResponse().getHeaders().get("Accept-Ranges").size());
	}
