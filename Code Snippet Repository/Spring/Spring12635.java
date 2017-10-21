	@Test
	public void testResourcesWithResolversTransformersCustom() throws Exception {
		loadBeanDefinitions("mvc-config-resources-chain-no-auto.xml");

		SimpleUrlHandlerMapping mapping = appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(mapping);
		assertNotNull(mapping.getUrlMap().get("/resources/**"));
		ResourceHttpRequestHandler handler = appContext.getBean((String) mapping.getUrlMap().get("/resources/**"),
				ResourceHttpRequestHandler.class);
		assertNotNull(handler);

		assertThat(handler.getCacheControl().getHeaderValue(),
				Matchers.equalTo(CacheControl.maxAge(1, TimeUnit.HOURS)
						.sMaxAge(30, TimeUnit.MINUTES).cachePublic().getHeaderValue()));

		List<ResourceResolver> resolvers = handler.getResourceResolvers();
		assertThat(resolvers, Matchers.hasSize(3));
		assertThat(resolvers.get(0), Matchers.instanceOf(VersionResourceResolver.class));
		assertThat(resolvers.get(1), Matchers.instanceOf(GzipResourceResolver.class));
		assertThat(resolvers.get(2), Matchers.instanceOf(PathResourceResolver.class));

		VersionResourceResolver versionResolver = (VersionResourceResolver) resolvers.get(0);
		assertThat(versionResolver.getStrategyMap().get("/**/*.js"),
				Matchers.instanceOf(FixedVersionStrategy.class));
		assertThat(versionResolver.getStrategyMap().get("/**"),
				Matchers.instanceOf(ContentVersionStrategy.class));

		List<ResourceTransformer> transformers = handler.getResourceTransformers();
		assertThat(transformers, Matchers.hasSize(2));
		assertThat(transformers.get(0), Matchers.instanceOf(CachingResourceTransformer.class));
		assertThat(transformers.get(1), Matchers.instanceOf(AppCacheManifestTransformer.class));
	}
