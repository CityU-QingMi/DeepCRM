	@Test
	public void testResourcesWithResolversTransformers() throws Exception {
		loadBeanDefinitions("mvc-config-resources-chain.xml");

		SimpleUrlHandlerMapping mapping = appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(mapping);
		assertNotNull(mapping.getUrlMap().get("/resources/**"));
		ResourceHttpRequestHandler handler = appContext.getBean((String) mapping.getUrlMap().get("/resources/**"),
				ResourceHttpRequestHandler.class);
		assertNotNull(handler);

		List<ResourceResolver> resolvers = handler.getResourceResolvers();
		assertThat(resolvers, Matchers.hasSize(4));
		assertThat(resolvers.get(0), Matchers.instanceOf(CachingResourceResolver.class));
		assertThat(resolvers.get(1), Matchers.instanceOf(VersionResourceResolver.class));
		assertThat(resolvers.get(2), Matchers.instanceOf(WebJarsResourceResolver.class));
		assertThat(resolvers.get(3), Matchers.instanceOf(PathResourceResolver.class));

		CachingResourceResolver cachingResolver = (CachingResourceResolver) resolvers.get(0);
		assertThat(cachingResolver.getCache(), Matchers.instanceOf(ConcurrentMapCache.class));
		assertEquals("test-resource-cache", cachingResolver.getCache().getName());

		VersionResourceResolver versionResolver = (VersionResourceResolver) resolvers.get(1);
		assertThat(versionResolver.getStrategyMap().get("/**/*.js"),
				Matchers.instanceOf(FixedVersionStrategy.class));
		assertThat(versionResolver.getStrategyMap().get("/**"),
				Matchers.instanceOf(ContentVersionStrategy.class));

		List<ResourceTransformer> transformers = handler.getResourceTransformers();
		assertThat(transformers, Matchers.hasSize(3));
		assertThat(transformers.get(0), Matchers.instanceOf(CachingResourceTransformer.class));
		assertThat(transformers.get(1), Matchers.instanceOf(CssLinkResourceTransformer.class));
		assertThat(transformers.get(2), Matchers.instanceOf(AppCacheManifestTransformer.class));

		CachingResourceTransformer cachingTransformer = (CachingResourceTransformer) transformers.get(0);
		assertThat(cachingTransformer.getCache(), Matchers.instanceOf(ConcurrentMapCache.class));
		assertEquals("test-resource-cache", cachingTransformer.getCache().getName());
	}
