	@Test
	public void bestPatternMatch() throws Exception {
		ResourceWebHandler otherHandler = new ResourceWebHandler();
		otherHandler.setLocations(this.locations);
		Map<String, VersionStrategy> versionStrategyMap = new HashMap<>();
		versionStrategyMap.put("/**", new ContentVersionStrategy());
		VersionResourceResolver versionResolver = new VersionResourceResolver();
		versionResolver.setStrategyMap(versionStrategyMap);

		List<ResourceResolver> resolvers = new ArrayList<>();
		resolvers.add(versionResolver);
		resolvers.add(new PathResourceResolver());
		otherHandler.setResourceResolvers(resolvers);

		this.handlerMap.put("/resources/*.css", otherHandler);
		this.urlProvider.registerHandlers(this.handlerMap);

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		String path = "/resources/foo.css";
		String url = this.urlProvider.getForUriString(path, exchange).block(Duration.ofSeconds(5));
		assertEquals("/resources/foo-e36d2e05253c6c7085a91522ce43a0b4.css", url);
	}
